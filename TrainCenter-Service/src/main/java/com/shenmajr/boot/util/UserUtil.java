package com.shenmajr.boot.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
	
	public static String getCurrentUser() {
		String username = SecurityContextHolder
				.getContext().getAuthentication().getName();
		return username;
	}

}
