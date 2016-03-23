package com.shenmajr.boot.service.imp.security;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.User;
import com.shenmajr.boot.repo.security.UserRepo;
import com.shenmajr.boot.service.security.GroupService;

public abstract class AbstractUserServiceImpl {

	private static final String[] IGNORE_PROPERTIES = {  "createdTime",
	"userDetail" };
	@Autowired
	protected UserRepo userRepo;
	
	@Autowired
	protected GroupService        groupService;
	
	public User create(final User user) {
	
	final User userEntity = userRepo.save(user);
	return userEntity;
	}
	
	public User update(final User user) {
	User userEntity = userRepo.getOne(user.getId());
	
	BeanUtils.copyProperties(user, userEntity, IGNORE_PROPERTIES);
	userEntity = userRepo.saveAndFlush(userEntity);
	return userEntity;
	}
	
	/**
	* 删除用户
	* 先根据用户找到所有用户所属的组
	* 然后 在每个组中删除此用户与组的关系
	* 最后直接删除用户
	* @param user
	*/
	@Transactional(rollbackFor = Exception.class)
	public void delete(final User user) {
	ArrayList<Group> groupList = (ArrayList<Group>) this.groupService.findAllByUser(user);
	
	for(Group g:groupList){
	    this.groupService.removeUser(g.getId(), user.getId());
	}
	
	userRepo.delete(user);
	}
	
	public void delete(final String id) {
	userRepo.delete(id);
	}
	
	public User get(final String id) {
	
	final User entity = userRepo.getOne(id);
	
	return entity;
	}
	
}
