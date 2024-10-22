package com.infomanager.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.infomanager.helpers.AppConstants;
import com.infomanager.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
	
	//Autowired Cloudinary object
	private Cloudinary cloudinary; 
	
	//constructor injection
	public ImageServiceImpl(Cloudinary cloudinary) {
		super();
		this.cloudinary = cloudinary;
	}
	
	// uploading Image in cloudinary
	@Override
    public String uploadImage(MultipartFile contactImage, String filename) {

        try {
        	//gives the size of the image in bytes, and the read(data) method reads the bytes into the data array
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", filename)); //You are passing the filename to public_id for easier image retrieval later.

            return this.getUrlFromPublicId(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

       

    }
	
	// The getUrlFromPublicId() method using Cloudinary's SDK(Cloud name, API Key, and API Secret) to generate the URL.
	
	@Override
	public String getUrlFromPublicId(String publicId) {
		//Specifies image transformations (e.g., width, height, and crop type).
		
		return cloudinary.url()
				         .transformation(
				        		 new Transformation<>().width(AppConstants.CONTACT_IMAGE_WIDTH)
				        		                       .height(AppConstants.CONTACT_IMAGE_HEIGHT)
				        		                       .crop(AppConstants.CONTACT_IMAGE_CROP))
				         .generate(publicId); 
				        		
	}

}
