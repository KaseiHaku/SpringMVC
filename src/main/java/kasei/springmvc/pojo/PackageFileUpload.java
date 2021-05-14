package kasei.springmvc.pojo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Arrays;

public class PackageFileUpload {
	private String name;
	private CommonsMultipartFile[] commonsMultipartFileAry;
	
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "PackageFileUpload [name=" + name + ", commonsMultipartFileAry="
				+ Arrays.toString(commonsMultipartFileAry) + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public CommonsMultipartFile[] getCommonsMultipartFileAry() {
		return commonsMultipartFileAry;
	}
	public void setCommonsMultipartFileAry(CommonsMultipartFile[] commonsMultipartFileAry) {
		this.commonsMultipartFileAry = commonsMultipartFileAry;
	}
	
}
