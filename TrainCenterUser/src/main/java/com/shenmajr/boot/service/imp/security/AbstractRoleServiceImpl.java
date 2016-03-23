package com.shenmajr.boot.service.imp.security;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.shenmajr.boot.domain.security.Role;
import com.shenmajr.boot.repo.security.RoleRepo;

public abstract class AbstractRoleServiceImpl {

	private static final String[] IGNORE_PROPERTIES = { "name", "groups",
	"createdTime" };
	@Autowired
	protected RoleRepo roleRepo;
	
	public Role create(final Role role) {
	final Role entity = roleRepo.saveAndFlush(role);
	return entity;
	}
	
	public Role update(final Role role) {
	final Role entity = roleRepo.getOne(role.getId());
	BeanUtils.copyProperties(role, entity, IGNORE_PROPERTIES);
	final Role entityEntity = roleRepo.saveAndFlush(entity);
	return entityEntity;
	}
	
	public void delete(final Role role) {
	roleRepo.delete(role);
	}
	
	public void delete(final String id) {
	roleRepo.delete(id);
	}
	
	public Role get(final String id) {
	
	final Role entity = roleRepo.getOne(id);
	
	return entity;
	}
}
