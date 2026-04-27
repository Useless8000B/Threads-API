package com.useless.threads_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(ObjectUtils.asMap(
			"cloud_name", System.getenv("CLOUD_NAME"),
			"api_key", System.getenv("API_KEY"),
			"api_secret", System.getenv("API_SECRET")
		));
	}
}
