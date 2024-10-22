package com.infomanager.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	//upload Image
	String uploadImage(MultipartFile contactImage, String filename);
	
	//get Url From PublicId
	String getUrlFromPublicId(String publicId);
}
