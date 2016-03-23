package com.shenmajr.boot.service.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.shenmajr.boot.domain.security.User;

public interface UserService {

	User create(User user);

    User update(User user);

    void delete(User user);// FIXME 删除需要同时删除依赖

    void delete(String id);// FIXME 删除需要同时删除依赖

    User get(String id);

    Page<User> findAll(Pageable page);

    Map<String, Object> searchAll(PageRequest pageRequest, HttpServletRequest request);
}
