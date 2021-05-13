package kasei.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

// 文件上传后端处理的三种方式
@Controller
public class FileUpload {
	
	
	// 方式一：直接以网络流的形式传输，并保存到本地，传输速度极慢，不建议使用
	@RequestMapping("mutiFileUpload.mvc")
	public void mutiFileUpload(@RequestParam("file") CommonsMultipartFile[] fileAry,
			HttpServletRequest request, ModelMap model) {
			
		// 设置本地存放上传文件的目录
		String targetPath = "E:\\fileUpload";
		File targetDirectory = new File(targetPath);
		if (!targetDirectory.exists()) {
			targetDirectory.mkdirs(); 
		}
		for (int i = 0; i < fileAry.length; i++) {  
			// 获得原始文件名  
	        String originalFileName = fileAry[i].getOriginalFilename();  
	          
	        // 新文件名  
	        String newFileName = UUID.randomUUID().toString();  
	        // 创建本地输出流文件
	        File targetFile = new File(targetPath+"/"+newFileName);
	        
	        if (!fileAry[i].isEmpty()) {  
	        	System.out.println("原始文件名:" + originalFileName);
	        	InputStream is = null;
				FileOutputStream fileOs = null;
	        	try {
					is = fileAry[i].getInputStream();
					fileOs = new FileOutputStream(targetFile, true);// 追加形式写入
					byte[] buffer = new byte[1024];
					Integer len = 0; // 实际读取的字节数
					while((len = is.read(buffer)) != -1) {
						fileOs.write(buffer, 0, len);
						fileOs.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if(is != null) {
							is.close();
						}
						if(fileOs != null) {
							fileOs.close();
						}					
					} catch (IOException e) {
						e.printStackTrace();
					}					
				}
	        }  
	    }  	   
	}
	
	
	// 方式二：springboot 配置文件配置 CommonsMultipartResolver 多部件解析器
	public void upload2(HttpServletRequest request) throws IllegalStateException, IOException {	
		/* 坑：
		  * 如果在 spring mvc 的配置文件中配置了  CommonsMultipartResolver 多部件解析器
		  * 当请求包含文件的时候自动调用了解析器解析成为 MultipartHttpServletRequest
		  * 所以函数参数中的 request 是不能放到 filter chain 中的 ，因为原来的 request 已经没了，
		  * */
		/** 以下代码是配置文件配置了解析器的情况 */
		MultipartHttpServletRequest multiRequest2 = (MultipartHttpServletRequest) request;
		List<MultipartFile> multipartFileList2 = multiRequest2.getFiles("文件前端传入的参数名"); // 获取文件
		Integer scoreId2 = Integer.parseInt(multiRequest2.getParameter("scoreId"));// 获取普通参数
		
			
		/** 以下代码是配置文件没有配置解析器的情况 */
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断请求中是否包含文件
		if(multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request; 
			List<MultipartFile> multipartFileList = multiRequest.getFiles("文件前端传入的参数名"); // 获取文件
			Integer scoreId = Integer.parseInt(multiRequest.getParameter("scoreId"));// 获取普通参数
			 
			Iterator<MultipartFile> iterator = multipartFileList.iterator();
			while(iterator.hasNext()) {
				MultipartFile multipartFile = iterator.next();
				if(multipartFile != null) {
					// 获取原文件名后缀
					String originalFileName  = multipartFile.getOriginalFilename();
					String suffix = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
					// 保存文件到本地
					multipartFile.transferTo(new File("E:/fileUpload/"+ UUID.randomUUID().toString() + suffix));
				}
			}
		}
	
	}
	
	
	// 方式三：直接使用 CommonsMultipartFile[] 类型来接收input file 参数	
	public void upload3(
			@RequestParam("file") CommonsMultipartFile[] fileAry,
			@RequestParam("name") String name) throws IllegalStateException, IOException {
		fileAry[0].transferTo(new File("")); // 跟上面一样
	}
	
}
