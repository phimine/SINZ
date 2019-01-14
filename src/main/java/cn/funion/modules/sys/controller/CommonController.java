package cn.funion.modules.sys.controller;

import cn.funion.common.utils.DateUtils;
import cn.funion.common.utils.R;
import cn.funion.common.utils.UUIDGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;


@RestController
@RequestMapping("sys/common")
public class CommonController{
	@Value("${sinz.static.file.path}")
	private String PATH;
	
	
	@Value("${sinz.static.file.basePath}")
	private String BASE_PATH;
	
	@Value("${sinz.domain_url}")
	private String DOMAIN_URL;
	
	
	private static final String SUB_PATH = "activity";
    /**
     * 列表
     */
    @RequestMapping("/upload")
	@RequiresPermissions("sys:common:upload")
    public R upload(@RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest req){
    	 if (file!=null) {
			try {
				String url = this.uploadFile(file);
				return R.ok().put("url", url);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		 }
        return R.ok();
    }
    private String uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			String basePath = BASE_PATH+SUB_PATH;
			File baseFile = new File(basePath);
			if (!baseFile.exists()) {
				baseFile.mkdirs();
			}
			String parentFileName = DateUtils.format(new Date(),"yyyy_MM_dd");
			basePath = basePath + "/" + parentFileName;
			baseFile = new File(basePath);
			if (!baseFile.exists()) {
				baseFile.mkdirs();
			}
			String name = UUIDGenerator.get32UUID();
			String sourceFinalFilePath = baseFile + "/" + name + "." + suffix;
			File sourcefinalFile = new File(sourceFinalFilePath);
			if (sourcefinalFile.exists()) {
				sourcefinalFile.delete();
			}
			file.transferTo(sourcefinalFile);
			return DOMAIN_URL +PATH+SUB_PATH + "/" + parentFileName + "/" + name + "."+ suffix;
		} else {
			return null;
		}
	}


}
