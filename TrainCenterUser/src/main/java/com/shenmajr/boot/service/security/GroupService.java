package com.shenmajr.boot.service.security;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.User;

public interface GroupService {
	Group create(Group group);

	Group update(Group group);

	Group get(String id);

	void delete(Group group);// FIXME 删除需要同时删除依赖

	void delete(String id);// FIXME 删除需要同时删除依赖

	Group addUser(String groupId, String userId);

	Group addUserByName(String groupId, String userName);

	Group removeUser(String groupId, String userId);

	Group removeUserByName(String groupId, String userName);

	Page<Group> findAll(Pageable page);

	Page<Group> findByUserPage(User user, Pageable page);

	Collection<Group> findAllByUser(User user);

	Collection<Group> findAllByUserId(String userId);

	Group addRoleByName(String groupId, String name);

	Group removeRoleByName(String groupId, String name);

	Group addRole(String groupId, String roleId);

	Group removeRole(String groupId, String roleId);

	boolean userInGroup(String userId, String groupId);

	Page<User> findUserByGroupId(String groupId, Pageable page);
	
	Group findByName(String name);

}
