package com.shenmajr.boot.service.imp.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import com.shenmajr.boot.domain.security.User;

public class UserBCryptServiceImpl extends WebUserServiceImp {

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User create(final User user) {
		Assert.notNull(user, "用户不能为空.");
		Assert.hasLength(user.getPassword(), "密码不能为空");
		final String encoded = passwordEncoder.encode(user.getPassword());
		user.setPassword(encoded);
		return super.create(user);
	}

	@Override
	public User update(final User user) {
		Assert.notNull(user, "用户不能为空.");
		Assert.hasLength(user.getPassword(), "密码不能为空");
		final String encoded = passwordEncoder.encode(user.getPassword());
		user.setPassword(encoded);
		return super.update(user);
	}
}
