package com.cos.crud.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cos.crud.utils.Utils;

@Controller
public class FileUploadController {
	
	@Value("${file.path}") 
	private String resourcePath;
	
	@PostMapping("/file/upload")
	public @ResponseBody String imageUpload(
			@RequestParam("file") MultipartFile file) {
	
		//File file = Utils.resize(file);
		UUID uuid = UUID.randomUUID();
		String uuidFileName = uuid+"_"+file.getOriginalFilename();
		
		Path filePath = Paths.get(resourcePath+uuidFileName);
		System.out.println("filePath : "+filePath);
		try {
			Files.write(filePath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filePath.toString();
	}
	
	public String aviUpload() {
		//File file = Utils.resize(file);
		return null;
	}
	
	public String fileUpload() {
		//File file = Utils.resize(file);
		return null;
	}
	
	//.. 10개!!
	
}












