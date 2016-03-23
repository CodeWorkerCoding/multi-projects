package com.shenmajr.boot.service.imp.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shenmajr.boot.domain.security.User;
import com.shenmajr.boot.repo.security.GroupRepo;
import com.shenmajr.boot.repo.security.RoleRepo;
import com.shenmajr.boot.service.security.UserService;

@Service(value="webUserService")
@Transactional
public class WebUserServiceImp extends AbstractUserServiceImpl
	implements UserService {

	@Autowired
    protected GroupRepo groupRepo;
    @Autowired
    protected RoleRepo  roleRepo;

    @Override
    public Page<User> findAll(final Pageable page) {
        return userRepo.findAll(page);
    }

    @Override
    public Map<String, Object> searchAll(PageRequest pageRequest, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<String, Object>();
        String username = request.getParameter("name");
        if (username == null) {
            username = "";
        }
        Page<User> pages = userRepo.searchByUserName("%" + username + "%", pageRequest);
        model.put("name", username);
        model.put("users", pages.getContent());
        model.put("page", pages);
        return model;
    }

}
