package com.kgovt.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.RandomStringUtils;
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
	 
	 public static String generateCommonLangPassword() {
		    String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
		    String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
		    String numbers = RandomStringUtils.randomNumeric(2);
		    String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
		    String totalChars = RandomStringUtils.randomAlphanumeric(2);
		    String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
		      .concat(numbers)
		      .concat(specialChar)
		      .concat(totalChars);
		    List<Character> pwdChars = combinedChars.chars()
		      .mapToObj(c -> (char) c)
		      .collect(Collectors.toList());
		    Collections.shuffle(pwdChars);
		    String password = pwdChars.stream()
		      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
		      .toString();
		    return password;
		}
}
