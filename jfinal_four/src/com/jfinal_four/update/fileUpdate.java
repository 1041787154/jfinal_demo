package com.jfinal_four.update;



import org.apache.tomcat.util.http.fileupload.FileUpload;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

public class fileUpdate extends Controller{

	public void index() {
		renderFreeMarker("index.html");
	}
	public void doUpdate() {
		UploadFile uploadFile = getFile("filename");
		String title = getPara("title");
		System.out.println("title:"+title);
		System.out.println("uploadFile:"+uploadFile);
		renderText("success!");
	}
}
