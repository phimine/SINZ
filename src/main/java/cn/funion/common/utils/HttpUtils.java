package cn.funion.common.utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * HTTP接口请求工具
 */
public class HttpUtils {

	private final static Logger logger = LoggerFactory
			.getLogger(HttpUtils.class);

	public final static String UTF8 = "UTF-8";
	private static CloseableHttpClient httpClient;

	static{
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(200);
		connectionManager.setDefaultMaxPerRoute(20);//例如默认每路由最高50并发，具体依据业务来定

		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator
						(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase
							("timeout")) {
						return Long.parseLong(value) * 1000;
					}
				}
				return 60 * 1000;//如果没有约定，则默认定义时长为60s
			}
		};

		// 创建Http请求配置参数
		RequestConfig requestConfig = RequestConfig.custom()
				// 获取连接超时时间
				.setConnectionRequestTimeout(10*1000)
				// 请求超时时间
				.setConnectTimeout(30*1000)
				// 响应超时时间
				.setSocketTimeout(30*1000)
				.build();

		httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager)
				.setKeepAliveStrategy(myStrategy)
				.setDefaultRequestConfig(requestConfig.custom().setStaleConnectionCheckEnabled(true).build())
				.build();
		//定期清理时效的链接
		IdleConnectionMonitorThread idleConnectionMonitorThread = new IdleConnectionMonitorThread(connectionManager);
		idleConnectionMonitorThread.start();
	}



	/**
	 * GET方式调用接口，编码缺省使用UTF-8
	 *
	 * @param url
	 *            接口url

	 * @return 请求结果
	 * @throws Exception
	 *             请求异常，网络连接失败等
	 */
	public static JSONObject get(String url)  {
		HttpResult result = get(url,null,null,null);
		if(result.getCode() != 200){return null;}
		return JSONObject.fromObject(result.getResult());
	}

	public static JSONObject get(String url,Map params)  {
		HttpResult result = get(url,params,null,null);
		if(result.getCode() != 200){return null;}
		return JSONObject.fromObject(result.getResult());
	}

	public static JSONObject get(String url,Map params,Map headers)  {
		HttpResult result = get(url,params,headers,null);
		if(result.getCode() != 200){return null;}
		return JSONObject.fromObject(result.getResult());
	}

	public static Object get(String url,Map<String,String> headers,boolean isArray){
		HttpResult result = get(url,null,headers,null);
		if(result.getCode() != 200){return null;}
		if(isArray){
			try{
				return JSONArray.fromObject(result.getResult());
			}catch (Exception e){
				logger.error("JSONArry format exception:",e);
				return  null;
			}

		}else{
			return JSONObject.fromObject(result.getResult());
		}
	}

	public static boolean delete(String url,Map<String,String> params,Map<String,String> headers,String charset){
		HttpResult result = getOrDelete(url,params,headers,charset,true);
		if(result.getCode() ==204 || result.getCode() ==200){
			return true;
		}
		return  false;
	}

	/**
	 * GET方式调用接口
	 *
	 * @param url
	 *            接口url
	 * @param params
	 *            参数，可为null
	 * @param headers
	 *            头信息，可为null
	 * @param charset
	 *            编码，缺省使用UTF-8
	 * @return 请求结果
	 * @throws Exception
	 *             请求异常，网络连接失败等
	 */
	public static HttpResult getOrDelete(String url, Map<String, String> params,
										 Map<String, String> headers, String charset,boolean isDelete)  {
		final String xFunctionName = "get()";
		logger.info(xFunctionName + ".begin");
		long begin = System.currentTimeMillis();
		HttpResult ret = new HttpResult();
		try {
			logger.info("接口url：" + url);


			if (StringUtils.isBlank(charset)) {
				charset = UTF8;
			}

			// 添加参数
			url = wrapperGetParams(url, params, charset);

			HttpRequestBase method = null;
			if(isDelete){
				method = new HttpDelete(url);
			}else {
				method = new HttpGet(url);
			}


			// 添加header
			if (null != headers) {
				for (Iterator<Entry<String, String>> iterator = headers
						.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, String> entry = iterator.next();
					method.addHeader(entry.getKey(), entry.getValue());
				}
			}

			CloseableHttpResponse response = httpClient.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ret.setResult(EntityUtils.toString(entity));
			}
			ret.setCode(response.getStatusLine().getStatusCode());
//			ret.setDatas(method.getResponseBody());
			logger.info(url+"耗时"+(System.currentTimeMillis()-begin));
			logger.info("接口返回状态：" + response.getStatusLine().getStatusCode());
			logger.info("接口返回值：" + ret.getResult());

			return ret;

		} catch (Exception e) {
			logger.error(xFunctionName, e);
			return null;
		} finally {
			logger.info(xFunctionName + ".end");
		}
	}

	private static String wrapperGetParams(String url, Map<String, String> params, String charset) throws UnsupportedEncodingException {
		if (null != params) {
			StringBuffer sb = new StringBuffer();
			for (Iterator<Entry<String, String>> iterator = params
					.entrySet().iterator(); iterator.hasNext(); ) {
				Entry<String, String> entry = iterator.next();
				sb.append("&")
						.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode(entry.getValue(), charset));
			}
			if (url.indexOf("?") < 0) {
				sb = sb.replace(0, 1, "?");
			}
			logger.info("接口参数：" + sb.toString());

			url += sb.toString();
		}
		return url;
	}

	/**
	 * GET方式调用接口
	 *
	 * @param url
	 *            接口url
	 * @param params
	 *            参数，可为null
	 * @param headers
	 *            头信息，可为null
	 * @param charset
	 *            编码，缺省使用UTF-8
	 * @return 请求结果
	 * @throws Exception
	 *             请求异常，网络连接失败等
	 */
	public static HttpResult get(String url, Map<String, String> params,
								 Map<String, String> headers, String charset)  {
		return getOrDelete(url,params,headers,charset,false);
	}

	/**
	 * POST方式调用接口，编码，缺省使用UTF-8
	 *
	 * @param url
	 *            接口url
	 * @return 请求结果
	 * @throws Exception
	 *             请求异常，网络连接失败等
	 */
	public static JSONObject post(String url) throws Exception {
		return post(url, null, null, null);
	}

	/**
	 * POST方式调用接口
	 *
	 * @param url
	 *            接口url
	 * @param params
	 *            参数，可为null
	 * @param headers
	 *            头信息，可为null
	 * @param charset
	 *            编码，缺省使用UTF-8
	 * @return 请求结果
	 * @throws Exception
	 *             请求异常，网络连接失败等
	 */
	public static JSONObject post(String url, Map<String, String> params,
								  Map<String, String> headers, String charset)  {
		final String xFunctionName = "post()";
		logger.info(xFunctionName + ".begin");
		HttpResult ret = new HttpResult();
		Long begin = System.currentTimeMillis();
		try {
			logger.info("接口url：" + url);
			if (StringUtils.isBlank(charset)) {
				charset = UTF8;
			}


			HttpPost method = new HttpPost(url);

			// method.setRequestHeader("Content-Type", "*; charset=UTF-8");

			// 添加header
			if (null != headers) {
				for (Iterator<Entry<String, String>> iterator = headers
						.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, String> entry = iterator.next();
					method.addHeader(entry.getKey(), entry.getValue());
				}
			}

			// 添加参数
			if (null != params) {
				// 创建参数队列
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				for (Iterator<Entry<String, String>> iterator = params
						.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, String> entry = iterator.next();
					nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				method.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset != null ? charset : "UTF-8"));
			}

			CloseableHttpResponse response = httpClient.execute(method);
			HttpEntity repEntity = response.getEntity();
			if (repEntity != null) {
				ret.setResult(EntityUtils.toString(repEntity));
			}

			ret.setCode(response.getStatusLine().getStatusCode());

//			ret.setDuration();
			logger.info(url+"耗时"+(System.currentTimeMillis()-begin));
			logger.info("接口返回状态：" + response.getStatusLine().getStatusCode());
			logger.info("接口返回值：" + ret.getResult());

			return JSONObject.fromObject(ret.getResult());

		} catch (Exception e) {
			logger.error(xFunctionName, e);
			return null;
		} finally {
			logger.info(xFunctionName + ".end");
		}
	}

	/**
	 * POST方式调用接口
	 *
	 * @param url
	 *            接口url
	 * @param entity
	 *            请求消息，不可为null
	 * @param headers
	 *            头信息，可为null
	 * @param charset
	 *            编码，缺省使用UTF-8
	 * @return 请求结果
	 * @throws Exception
	 *             请求异常，网络连接失败等
	 */
	public static JSONObject postEntity(String url, HttpEntity entity,
										Map<String, String> headers, String charset) throws Exception {
		final String xFunctionName = "postEntity()";
		logger.info(xFunctionName + ".begin");

		HttpResult ret = new HttpResult();
		long begin = System.currentTimeMillis();
		try {
			logger.info("接口url：" + url);
			if (StringUtils.isBlank(charset)) {
				charset = UTF8;
			}


			HttpPost method = new HttpPost(url);
			method.setHeader("charset",charset);


			// method.setRequestHeader("Content-Type", "*; charset=UTF-8");

			// 添加header
			if (null != headers) {
				for (Iterator<Entry<String, String>> iterator = headers
						.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, String> entry = iterator.next();
					method.addHeader(entry.getKey(), entry.getValue());
				}
			}

			// 添加参数
			method.setEntity(entity);

			CloseableHttpResponse response = httpClient.execute(method);
			HttpEntity repEntity = response.getEntity();
			if (repEntity != null) {
				ret.setResult(EntityUtils.toString(repEntity));
			}
			ret.setCode(response.getStatusLine().getStatusCode());
//			ret.setDuration(durationUtils.stop());
			logger.info(url+"耗时"+(System.currentTimeMillis()-begin));
			logger.info("接口返回状态：" + response.getStatusLine().getStatusCode());
			logger.info("接口返回值：" + ret.getResult());

			return JSONObject.fromObject(ret.getResult());

		} catch (Exception e) {
			logger.error(xFunctionName, e);
			throw e;
		} finally {
			logger.info(xFunctionName + ".end");
		}
	}
	/**
	 * POST方式调用接口
	 *
	 * @param url
	 *            接口url
	 * @param body
	 *            参数，可为null
	 * @param headers
	 *            头信息，可为null
	 * @param charset
	 *            编码，缺省使用UTF-8
	 * @return 请求结果
	 * @throws Exception
	 *             请求异常，网络连接失败等
	 */
	@SuppressWarnings("deprecation")
	public static JSONObject patchBody(String url, Object body,
									   Map<String, String> headers, String charset)  {
		final String xFunctionName = "postBody()";
		logger.info(xFunctionName + ".begin");

		HttpResult ret = new HttpResult();
//		DurationUtils durationUtils = new DurationUtils();
//		durationUtils.start();
		long begin = System.currentTimeMillis();
		try {
			logger.info("接口url：" + url);
			if (StringUtils.isBlank(charset)) {
				charset = UTF8;
			}

//			HttpPatch patchmethod = new HttpPatch(url);
			HttpPatch method = new HttpPatch(url);

			// method.setRequestHeader("Content-Type", "*; charset=UTF-8");

			// 添加header
			if (null != headers) {
				for (Iterator<Entry<String, String>> iterator = headers
						.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, String> entry = iterator.next();
					method.addHeader(entry.getKey(), entry.getValue());
				}
			}

			// 添加参数
//			method.setRequestBody(body.toString());
			method.setEntity(new StringEntity(body.toString(), ContentType.create("application/json", "UTF-8")));

			CloseableHttpResponse response = httpClient.execute(method);
			HttpEntity repEntity = response.getEntity();
			if (repEntity != null) {
				ret.setResult(EntityUtils.toString(repEntity));
			}

			ret.setCode(response.getStatusLine().getStatusCode());
//			ret.setDuration(durationUtils.stop());
			logger.info(url+"耗时"+(System.currentTimeMillis()-begin));
			logger.info("接口返回状态：" + response.getStatusLine().getStatusCode());
			logger.info("接口返回值：" + ret.getResult());

			return JSONObject.fromObject(ret.getResult());

		} catch (Exception e) {
			logger.error(xFunctionName, e);
			return null;

		} finally {
			logger.info(xFunctionName + ".end");
		}
	}
	/**
	 * POST方式调用接口
	 *
	 * @param url
	 *            接口url
	 * @param body
	 *            参数，可为null
	 * @param headers
	 *            头信息，可为null
	 * @param charset
	 *            编码，缺省使用UTF-8
	 * @return 请求结果
	 * @throws Exception
	 *             请求异常，网络连接失败等
	 */
	@SuppressWarnings("deprecation")
	public static JSONObject postBody(String url, Object body,
									  Map<String, String> headers, String charset)  {
		final String xFunctionName = "postBody()";
		logger.info(xFunctionName + ".begin");

		HttpResult ret = new HttpResult();
//		DurationUtils durationUtils = new DurationUtils();
//		durationUtils.start();
		long begin = System.currentTimeMillis();
		try {
			logger.info("接口url：" + url);
			if (StringUtils.isBlank(charset)) {
				charset = UTF8;
			}

			HttpPost method = new HttpPost(url);

			// method.setRequestHeader("Content-Type", "*; charset=UTF-8");

			// 添加header
			if (null != headers) {
				for (Iterator<Entry<String, String>> iterator = headers
						.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, String> entry = iterator.next();
					method.addHeader(entry.getKey(), entry.getValue());
				}
			}

			// 添加参数
			if(body != null){
				StringEntity requestEntity = new StringEntity(body.toString(), ContentType.create("application/json", "UTF-8"));
				method.setEntity(requestEntity);
			}


			CloseableHttpResponse response = httpClient.execute(method);

			ret.setCode(response.getStatusLine().getStatusCode());

			HttpEntity repEntity = response.getEntity();
			if (repEntity != null) {
				ret.setResult(EntityUtils.toString(repEntity));
			}
//			ret.setDuration(durationUtils.stop());
			logger.info(url+"耗时"+(System.currentTimeMillis()-begin));
			logger.info("接口返回状态：" + response.getStatusLine().getStatusCode());
			logger.info("接口返回值：" + ret.getResult());

			return JSONObject.fromObject(ret.getResult());

		} catch (Exception e) {
			logger.error(xFunctionName, e);
			return null;

		} finally {
			logger.info(xFunctionName + ".end");
		}
	}

	/**
	 * http请求资源文件
	 * @param url
	 * @param fileMap 资源文件
	 * @param contextMap  内容  可为null
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static JSONObject postFile(String url,
									  Map<String, String> fileMap, Map<String, String> contextMap) throws Exception {
		final String xFunctionName = "postFile()";
		logger.info(xFunctionName + ".begin");
		long begin = System.currentTimeMillis();
//		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
			HttpResult ret = new HttpResult();
			Charset charSet = Charset.forName("UTF-8");
			MultipartEntityBuilder aa = MultipartEntityBuilder.create();

			Iterator<Entry<String, String>> itFile = fileMap.entrySet()
					.iterator();
			FileBody fileBody = null;
			while (itFile.hasNext()) {
				Entry<String, String> entry = itFile.next();
				fileBody = new FileBody(new File(entry.getValue()));
				aa.addPart(entry.getKey(), fileBody);
			}

			if(contextMap!=null){
				Iterator<Entry<String, String>> itContext = contextMap.entrySet()
						.iterator();
				StringBody context = null;
				while (itContext.hasNext()) {
					Entry<String, String> entry = itContext.next();
					context = new StringBody(entry.getValue().toString(), charSet);
					aa.addPart(entry.getKey(), context);
				}
			}

			HttpEntity reqEntity = aa.build();
			httppost.setEntity(reqEntity);
			HttpResponse response = httpClient.execute(httppost);
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity resEntity = response.getEntity();
			String str = EntityUtils.toString(resEntity);

			Map<String, String> map = JSONObject.fromObject(str); // httpclient自带的工具类读取返回数据
			ret.setCode(statusCode);
			ret.setResult(str);
//			ret.setDuration(durationUtils.stop());
			logger.info(url+"耗时"+(System.currentTimeMillis()-begin));
			logger.info("接口返回状态：" + statusCode);
			logger.info("接口返回值：" + map);

			return JSONObject.fromObject(ret.getResult());

		} catch (Exception e) {
			logger.error(xFunctionName, e);
			throw e;
		} finally {
			httpClient = null;
			logger.info(xFunctionName + ".end");
		}

	}


	/**
	 * 发送get请求
	 * @param url       链接地址
	 * @param charset   字符编码，若为null则默认utf-8
	 * @return
	 */
	public static String doGetSSL(String url,Map<String,String> params,String charset){
		if(null == charset){
			charset = "utf-8";
		}
//		org.apache.http.client.HttpClient httpClient = null;
		CloseableHttpClient httpclient = null;
		HttpGet httpGet= null;
		String result = null;

		try {
//			httpClient = HttpClients.createDefault();
			url =wrapperGetParams(url,params,charset);
			httpGet = new HttpGet(url);
			httpGet.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(httpGet);
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		} catch (Exception e) {
			logger.error("doGetSSL ", e);
		}

		return result;
	}
	/**
	 * 设置请求超时时间
	 */
//	private static void setTimeout(HttpClient client) {
//		client.getHttpConnectionManager().getParams()
//				.setConnectionTimeout(TIME_OUT);
//		client.getHttpConnectionManager().getParams().setSoTimeout(TIME_OUT);
//	}

	public static class HttpResult {
		private int code;
		private String result;
		private InputStream inputStream;
		private byte[] datas;
		private int duration;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public byte[] getDatas() {
			return datas;
		}

		public void setDatas(byte[] datas) {
			this.datas = datas;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

	}


	public static class IdleConnectionMonitorThread extends Thread {

		private final HttpClientConnectionManager connMgr;
		private volatile boolean shutdown;

		public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
			super();
			this.connMgr = connMgr;
		}

		@Override
		public void run() {
			try {
				while (!shutdown) {
					synchronized (this) {
						wait(5000);
						// Close expired connections
						connMgr.closeExpiredConnections();
						// Optionally, close connections
						// that have been idle longer than 30 sec
						connMgr.closeIdleConnections(60, TimeUnit.SECONDS);
					}
				}
			} catch (InterruptedException ex) {
				// terminate
			}
		}

		public void shutdown() {
			shutdown = true;
			synchronized (this) {
				notifyAll();
			}
		}

	}


	public static void main(String[] args) throws Exception {
		String sinzURL= "https://members.staging.enz.cloud";
		JSONObject params = new JSONObject();
		params.put("clientId", "332dce10-8312-11e8-b7e0-4dcaba0388f2");
		params.put("clientSecret","chah{sa7woox2Eir");
		Map<String,String> headss = new HashMap<>();
		headss.put("Content-Type", "application/json");
		JSONObject result =HttpUtils.postBody("https://members.staging.enz.cloud/auth", params, headss, null);
		System.out.println(result);

		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+result.get("token"));
		heads.put("Content-Type", "application/json");
		JSONObject paramss = new JSONObject();
		params.put("body", "测试中文");
		HttpResult jsonResult =  HttpUtils.get(sinzURL+"/members/"+720+"/shortlist/"+1287L+"/notes",null,heads,null);
		System.out.println(jsonResult.getResult());
	}
}
