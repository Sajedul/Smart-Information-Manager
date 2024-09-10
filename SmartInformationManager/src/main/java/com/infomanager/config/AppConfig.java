package com.infomanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {
	
	@Value("${cloudinary.cloud.name}")
	private String cloudName;
	@Value("${cloudinary.api.key}")
	private String apiKey;
	@Value("${cloudinary.api.secret}")
	private String apiSecret;
	
	//The method returns a Cloudinary object that can be injected into other parts of the application.
	//Cloudinary object using configuration parameters such as cloud_name, api_key, and api_secret. 
	//These are the credentials required to authenticate with the Cloudinary API
	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(
				ObjectUtils.asMap(
						"cloud_name",cloudName,
						"api_key",apiKey,
						"api_secret",apiSecret)
				
				); 
	}
}
