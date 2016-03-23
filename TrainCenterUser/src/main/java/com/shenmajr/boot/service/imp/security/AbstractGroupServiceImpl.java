package com.shenmajr.boot.service.imp.security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.User;
import com.shenmajr.boot.repo.security.GroupRepo;
import com.shenmajr.boot.repo.security.UserRepo;

public abstract class AbstractGroupServiceImpl {

	@Autowired
	protected GroupRepo groupRepo;

	@Autowired
	protected UserRepo userRepo;

	private static final String[] IGNORE_PROPERTIES = {  "users",
			"createdTime" };

	public Group create(final Group group) {
		final Group entity = groupRepo.save(group);
		return entity;
	}

	public Group update(final Group group) {
		Group entity = groupRepo.getOne(group.getId());

		BeanUtils.copyProperties(group, entity, IGNORE_PROPERTIES);
		entity = groupRepo.save(entity);
		return entity;
	}

	public Group get(final String id) {

		final Group entity = groupRepo.getOne(id);

		return entity;
	}

	public void delete(final Group group) {
		groupRepo.delete(group);

	}

	public void delete(final String id) {
		groupRepo.delete(id);
	}


	@Transactional
	public boolean userInGroup(final String userId, final String gId) {
		Assert.notNull(userId);
		Assert.notNull(gId);
		final User user = userRepo.getOne(userId.trim());
		if (user == null) {
			throw new RuntimeException("用户不存在");
		}

		List<Group> groups = groupRepo.findAllByUserId(userId);
		for (Group g : groups) {
			if (g.getId().equals(gId.trim())) {
				return true;
			}
		}
		return false;
	}

}
