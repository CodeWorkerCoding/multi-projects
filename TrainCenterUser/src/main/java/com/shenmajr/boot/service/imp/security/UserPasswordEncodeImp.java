package com.shenmajr.boot.service.imp.security;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shenmajr.boot.domain.security.User;
import com.shenmajr.boot.service.security.UserPasswordEncode;
import com.shenmajr.boot.service.security.UserService;

public class UserPasswordEncodeImp implements UserPasswordEncode {

	private PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
	@Resource
	EntityManager EM;

	@Resource(name = "webUserService")
	UserService userService;

	@Transactional
	@Override
	public void encodeAll() {
		Pageable page = new PageRequest(0, 30);
		Page<User> pages = null;
		do {
			pages = userService.findAll(page);
			page = pages.nextPageable();

			for (final User user : pages.getContent()) {
				user.setPassword(passwordEnocder.encode(user.getPassword()));
			}
			EM.flush();
		} while (pages.hasNext());
	}
}
