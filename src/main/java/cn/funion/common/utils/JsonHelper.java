package cn.funion.common.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.util.*;


/**
 * @ClassName JsonHelper
 * @Description Json处理器
 * @author Alvin
 * @date Mar 28, 2012 2:40:16 PM
 * @version V 1.0
 */
public class JsonHelper {

	private static final long	serialVersionUID	= -3674498965562297865L;
	private static Logger		logger				= Logger.getLogger(JsonHelper.class);

	/**
	 * 将不含日期时间格式的Java对象序列化为Json资料格式
	 * 
	 * @param pObject 传入的Java对象
	 * @return
	 */
	public static final String encodeObject2Json(Object pObject) {
		String jsonString = "[]";
		if (pObject==null) {
			logger.warn("传入的Java对象为空,不能将其序列化为Json资料格式.请检查!");
		} else {
			if (pObject instanceof ArrayList) {

				JSONArray jsonArray = JSONArray.fromObject(pObject);
				jsonString = jsonArray.toString();
			} else {
				JSONObject jsonObject = JSONObject.fromObject(pObject);
				jsonString = jsonObject.toString();
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("序列化后的JSON资料输出:\n" + jsonString);
		}
		return jsonString;
	}



	/***************************************************************************
	 * 将List对象序列化为JSON文本
	 */
	public static <T> String toJSONString(List<T> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	/***************************************************************************
	 * 将对象序列化为JSON文本
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	/***************************************************************************
	 * 将JSON对象数组序列化为JSON文本
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static String toJSONString(JSONArray jsonArray) {
		return jsonArray.toString();
	}

	/***************************************************************************
	 * 将JSON对象序列化为JSON文本
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static String toJSONString(JSONObject jsonObject) {
		return jsonObject.toString();
	}

	/***************************************************************************
	 * 将对象转换为List对象
	 * 
	 * @param object
	 * @return
	 */

	public static List<Object> toArrayList(Object object) {
		List<Object> arrayList = new ArrayList<Object>();
		JSONArray jsonArray = JSONArray.fromObject(object);
		Iterator it = jsonArray.iterator();
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();
			Iterator keys = jsonObject.keys();
			while (keys.hasNext()) {
				Object key = keys.next();
				Object value = jsonObject.get(key);
				arrayList.add(value);
			}
		}
		return arrayList;
	}

	/***************************************************************************
	 * 将对象转换为HashMap
	 * 
	 * @param object
	 * @return
	 */
	public static HashMap toHashMap(Object object) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		JSONObject jsonObject = toJSONObject(object);
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			data.put(key, value);
		}
		return data;
	}

	/***************************************************************************
	 * 将对象转换为List<Map<String,Object>>
	 * 
	 * @param object
	 * @return
	 */

	// 返回非实体类型(Map<String,Object>)的List
	public static List<Map<String, Object>> toList(Object object) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = JSONArray.fromObject(object);
		for (Object obj : jsonArray) {
			JSONObject jsonObject = (JSONObject) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				Object value = jsonObject.get(key);
				map.put((String) key, value);
			}
			list.add(map);
		}
		return list;
	}

	/***************************************************************************
	 * 将对象转换为Collection对象
	 * 
	 * @param object
	 * @return
	 */
	public static Collection toCollection(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return JSONArray.toCollection(jsonArray);
	}

	/***************************************************************************
	 * 将对象转换为JSON对象数组
	 * 
	 * @param object
	 * @return
	 */
	public static JSONArray toJSONArray(Object object) {
		return JSONArray.fromObject(object);
	}

	/***************************************************************************
	 * 将对象转换为JSON对象
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject toJSONObject(Object object) {
		return JSONObject.fromObject(object);
	}

	/***************************************************************************
	 * 将JSON对象数组转换为传入类型的List
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param objectClass
	 * @return
	 */
	public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
		return JSONArray.toList(jsonArray, objectClass);
	}

	/***************************************************************************
	 * 将对象转换为传入类型的List
	 * 
	 * @param <T>
	 * @param object
	 * @param objectClass
	 * @return
	 */
	public static <T> List<T> toList(Object object, Class<T> objectClass) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return JSONArray.toList(jsonArray, objectClass);
	}
	
	/***************************************************************************
	 * 将对象转换为传入类型的List
	 * 
	 * @param <T>
	 * @param jsonStr
	 * @param objectClass
	 * @return
	 */
	public static <T> List<T> toList(String jsonStr, Class<T> objectClass) {
		JSONObject object = JSONObject.fromObject(jsonStr); 
		JSONArray jsonArray = JSONArray.fromObject(object);
		return JSONArray.toList(jsonArray, objectClass);
	}

	/***************************************************************************
	 * 将JSON对象转换为传入类型的对象
	 * 
	 * @param <T>
	 * @param jsonObject
	 * @param beanClass
	 * @return
	 */
	public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {
		return (T) JSONObject.toBean(jsonObject, beanClass);
	}
	
	/***************************************************************************
	 * 将JSON字符串转换为传入类型的对象
	 * 
	 * @param <T>
	 * @param jsonStr
	 * @param beanClass
	 * @return
	 */
	public static <T> T toBean(String jsonStr, Class<T> beanClass) {
		JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
		return (T) JSONObject.toBean(jsonBean, beanClass);
	}

	/***************************************************************************
	 * 将将对象转换为传入类型的对象
	 * 
	 * @param <T>
	 * @param object
	 * @param beanClass
	 * @return
	 */
	public static <T> T toBean(Object object, Class<T> beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return (T) JSONObject.toBean(jsonObject, beanClass);
	}

	/***************************************************************************
	 * 将JSON文本反序列化为主从关系的实体
	 * 
	 * @param <T>
	 *            泛型T 代表主实体类型
	 * @param <D>
	 *            泛型D 代表从实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailName
	 *            从实体类在主实体类中的属性名称
	 * @param detailClass
	 *            从实体类型
	 */
	public static <T, D> T toBean(String jsonString, Class<T> mainClass, String detailName, Class<D> detailClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray = (JSONArray) jsonObject.get(detailName);
		T mainEntity = toBean(jsonObject, mainClass);
		List<D> detailList = toList(jsonArray, detailClass);
		try {
			BeanUtils.setProperty(mainEntity, detailName, detailList);
		} catch (Exception ex) {
			throw new RuntimeException("主从关系JSON反序列化实体失败！");
		}
		return mainEntity;
	}

	/***************************************************************************
	 * 将JSON文本反序列化为主从关系的实体
	 * 
	 * @param <T>泛型T
	 *            代表主实体类型
	 * @param <D1>泛型D1
	 *            代表从实体类型
	 * @param <D2>泛型D2
	 *            代表从实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailName1
	 *            从实体类在主实体类中的属性
	 * @param detailClass1
	 *            从实体类型
	 * @param detailName2
	 *            从实体类在主实体类中的属性
	 * @param detailClass2
	 *            从实体类型
	 * @return
	 */
	public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass, String detailName1, Class<D1> detailClass1, String detailName2,
			Class<D2> detailClass2) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
		JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);
		T mainEntity = toBean(jsonObject, mainClass);
		List<D1> detailList1 = toList(jsonArray1, detailClass1);
		List<D2> detailList2 = toList(jsonArray2, detailClass2);
		try {
			BeanUtils.setProperty(mainEntity, detailName1, detailList1);
			BeanUtils.setProperty(mainEntity, detailName2, detailList2);
		} catch (Exception ex) {
			throw new RuntimeException("主从关系JSON反序列化实体失败！");
		}
		return mainEntity;
	}

	/***************************************************************************
	 * 将JSON文本反序列化为主从关系的实体
	 * 
	 * @param <T>泛型T
	 *            代表主实体类型
	 * @param <D1>泛型D1
	 *            代表从实体类型
	 * @param <D2>泛型D2
	 *            代表从实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailName1
	 *            从实体类在主实体类中的属性
	 * @param detailClass1
	 *            从实体类型
	 * @param detailName2
	 *            从实体类在主实体类中的属性
	 * @param detailClass2
	 *            从实体类型
	 * @param detailName3
	 *            从实体类在主实体类中的属性
	 * @param detailClass3
	 *            从实体类型
	 * @return
	 */

	public static <T, D1, D2, D3> T toBean(String jsonString, Class<T> mainClass, String detailName1, Class<D1> detailClass1, String detailName2,
			Class<D2> detailClass2, String detailName3, Class<D3> detailClass3) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
		JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);
		JSONArray jsonArray3 = (JSONArray) jsonObject.get(detailName3);
		T mainEntity = toBean(jsonObject, mainClass);
		List<D1> detailList1 = toList(jsonArray1, detailClass1);
		List<D2> detailList2 = toList(jsonArray2, detailClass2);
		List<D3> detailList3 = toList(jsonArray3, detailClass3);
		try {
			BeanUtils.setProperty(mainEntity, detailName1, detailList1);
			BeanUtils.setProperty(mainEntity, detailName2, detailList2);
			BeanUtils.setProperty(mainEntity, detailName3, detailList3);
		} catch (Exception ex) {
			throw new RuntimeException("主从关系JSON反序列化实体失败！");
		}
		return mainEntity;
	}

	/***************************************************************************
	 * 将JSON文本反序列化为主从关系的实体
	 * 
	 * @param <T>
	 *            主实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailClass
	 *            存放了多个从实体在主实体中属性名称和类型
	 * @return
	 */
	public static <T> T toBean(String jsonString, Class<T> mainClass, HashMap<String, Class> detailClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T mainEntity = toBean(jsonObject, mainClass);
		for (Object key : detailClass.keySet()) {
			try {
				Class value = (Class) detailClass.get(key);
				BeanUtils.setProperty(mainEntity, key.toString(), value);
			} catch (Exception ex) {
				throw new RuntimeException("主从关系JSON反序列化实体失败！");
			}
		}
		return mainEntity;
	}
	
	public static <T> T getBean(String jsonString, Class<T> beanClass){
	 JSONObject jsonObj = JSONObject.fromObject(jsonString);
	 JsonConfig config = new JsonConfig();
        config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {
            @Override
            public String transformToJavaIdentifier(String str) {
                char[] chars = str.toCharArray();
                chars[0] = Character.toLowerCase(chars[0]);
                return new String(chars);
            }
        });
        config.setRootClass(beanClass);
        Object bean = JSONObject.toBean(jsonObj, config);
        if(bean==null){
        	return null;
        }
        return beanClass.cast(bean);
}
	
	public static <T> T getBean(JSONObject jsonObj, Class<T> beanClass){
		 JsonConfig config = new JsonConfig();
	        config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {
	            @Override
	            public String transformToJavaIdentifier(String str) {
	                char[] chars = str.toCharArray();
	                chars[0] = Character.toLowerCase(chars[0]);
	                return new String(chars);
	            }
	        });
	        config.setRootClass(beanClass);
	        Object bean = JSONObject.toBean(jsonObj, config);
	        if(bean==null){
	        	return null;
	        }
	        return beanClass.cast(bean);
	}
	
	
	public static <T, D> T getBean(String jsonString, Class<T> mainClass, String detailName, Class<D> detailClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Object listObj = jsonObject.get(detailName);
		T mainEntity = getBean(jsonObject, mainClass);
		if(listObj!=null){
			JSONArray jsonArray = jsonObject.getJSONArray(detailName);
			if(jsonArray!=null&&jsonArray.size()>0){
				List<D> detailList =new ArrayList<D>();
				for (Object object : jsonArray) {
					detailList.add(getBean(object.toString(), detailClass));
				}
				try {
					BeanUtils.setProperty(mainEntity, lowerCharFirst(detailName), detailList);
				} catch (Exception ex) {
					throw new RuntimeException("主从关系JSON反序列化实体失败！");
				}
			}
		}
		return mainEntity;
	}
	
	public static String lowerCharFirst(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
	
	
	/**
     * 将字符串转为对象
     * 
     * @param <T>
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> T str2obj(String jsonStr, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        try {
        	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            obj = mapper.readValue(jsonStr, cls);
        } catch (Exception e) {
        	logger.error("str2obj 出现异常",e);
        }
        return obj;
    }

	
	/**
     * 将字符串转list对象
     * 
     * @param <T>
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> List<T> str2list(String jsonStr, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> objList = null;
        try {
            JavaType t = mapper.getTypeFactory().constructParametricType(
                    List.class, cls);
            objList = mapper.readValue(jsonStr, t);
        } catch (Exception e) {
        	logger.error("str2list 出现异常",e);
        }
        return objList;
    }
    
    public static String obj2Json(Object obj){
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonStr = null;
    	try {
    		jsonStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("obj2Json 出现异常",e);
		}
    	return jsonStr;
    }
}
