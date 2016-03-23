package com.shenmajr.boot.service.imp.security;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.Role;
import com.shenmajr.boot.domain.security.User;
import com.shenmajr.boot.repo.security.GroupRepo;
import com.shenmajr.boot.repo.security.UserRepo;
import com.shenmajr.boot.service.security.RoleService;

public class WebRoleServiceImp extends AbstractRoleServiceImpl
	implements RoleService {

	@Autowired
	protected GroupRepo groupRepo;
	@Autowired
	protected UserRepo userRepo;

	@Override
	public Role addGroup(final String roleId, final String gId) {

		Assert.notNull(roleId, "角色不能为空");
		Assert.notNull(gId, "组不能为空");

		final String groupId = gId.trim();
		final Group groupEntity = groupRepo.getOne(groupId);
		final Role roleEntity = roleRepo.getOne(roleId);

		if (roleEntity.getGroups() == null) {
			roleEntity.setGroups(new HashSet<Group>());
		}
		roleEntity.getGroups().add(groupEntity);

		groupRepo.saveAndFlush(groupEntity);

		return roleEntity;
	}

	@Override
	public Role removeGroup(final String roleId, final String groupId) {

		Assert.notNull(roleId, "角色不能为空");
		Assert.notNull(groupId, "组不能为空");

		final Group groupEntity = groupRepo.getOne(groupId);
		final Role roleEntity = roleRepo.getOne(roleId);

		if (roleEntity.getGroups() == null) {
			return roleEntity;
		}

		roleEntity.getGroups().remove(groupEntity);

		groupRepo.saveAndFlush(groupEntity);

		return roleEntity;
	}

	@Override
	public Page<Role> findAll(final Pageable page) {
		Assert.notNull(page);
		return roleRepo.findAll(page);
	}

	@Override
	public Page<Role> findByGroup(final Group group, final Pageable pageable) {
		Assert.notNull(pageable);
		Assert.notNull(group);

		return roleRepo.findByGroup(group, pageable);
	}

	@Override
	public Page<Role> findByGroupId(final String groupId, final Pageable page) {
		Assert.notNull(page);
		Assert.notNull(groupId);

		return roleRepo.findByGroupId(groupId, page);
	}

	@Override
	public Page<Group> findGroupByRoleId(final String roleId,
			final Pageable page) {
		Assert.notNull(roleId);
		Assert.notNull(page);
		return roleRepo.findGroupByRoleId(roleId, page);
	}

	@Override
	public Role addGroupByName(final String roleId, final String groupName) {
		Assert.notNull(roleId);
		Assert.notNull(groupName);

		final Group groupEntity = groupRepo.findByName(groupName);
		return addGroup(roleId, groupEntity.getId());
	}

	@Override
	public Role removeGroupByName(final String roleId, final String groupName) {
		Assert.notNull(roleId);
		Assert.notNull(groupName);

		final Group groupEntity = groupRepo.findByName(groupName);
		return removeGroup(roleId, groupEntity.getId());
	}

	@Override
	public List<Role> findAllByUserId(String userId) {
		return roleRepo.findAllByUserId(userId);
	}

	@Override
	public List<Role> findAllByUser(User user) {
		return roleRepo.findAllByUserId(user.getId());
	}

}
