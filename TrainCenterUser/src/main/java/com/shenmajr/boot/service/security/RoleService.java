package com.shenmajr.boot.service.security;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.Role;
import com.shenmajr.boot.domain.security.User;

public interface RoleService {
	Role create(Role role);

	Role update(Role role);

	void delete(Role role);

	void delete(String id);// FIXME 删除需要同时删除依赖

	Role get(String id);

	Page<Role> findAll(Pageable page);

	// Role changeParent(String roleId, String parent);

	Role addGroup(String roleId, String groupId);

	Role addGroupByName(String roleId, String groupName);

	Role removeGroup(String roleId, String groupId);

	Role removeGroupByName(String roleId, String groupName);

	// Role addUser(String roleId, String userId);

	// Role removeUser(String roleId, String userId);

	Page<Role> findByGroup(Group group, Pageable pageable);

	Page<Role> findByGroupId(String groupId, Pageable page);

	Page<Group> findGroupByRoleId(String roleId, Pageable page);

	List<Role> findAllByUserId(String userId);

	List<Role> findAllByUser(User user);
}
