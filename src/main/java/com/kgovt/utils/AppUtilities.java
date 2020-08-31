package com.kgovt.utils;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

import com.kgovt.models.ApplicationDetailes;

public class AppUtilities {
	
	/**
	 * isNotNullAndNotEmpty is used to check given number is not null or not empty
	 * @param value
	 * @return true if string is not null and not empty
	 */
	public static boolean isNotNullAndNotEmpty(String value) {
		if(null == value) {
			return false;
		}
		value = value.trim();
		return value.length() >= 1 && !"null".equalsIgnoreCase(value);
	}
	
	public static String generateReceptNo(ApplicationDetailes applicationDetailes) {
		String prefix = String.valueOf(applicationDetailes.getApplicantNumber());
		String sufix = String.valueOf(applicationDetailes.getMobile());
		String seperator = "-";
		String receiptNo = prefix + seperator + sufix;
		return receiptNo;
	}
	
	 public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
	        // application/pdf
	        // application/xml
	        // image/gif, ...
	        String mineType = servletContext.getMimeType(fileName);
	        try {
	            MediaType mediaType = MediaType.parseMediaType(mineType);
	            return mediaType;
	        } catch (Exception e) {
	            return MediaType.APPLICATION_OCTET_STREAM;
	        }
	    }
}
