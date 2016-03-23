package com.shenmajr.boot.service.imp.security;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.Role;
import com.shenmajr.boot.domain.security.User;
import com.shenmajr.boot.repo.security.RoleRepo;
import com.shenmajr.boot.service.security.GroupService;

@Service
public class WebGroupServiceImp extends AbstractGroupServiceImpl
	implements GroupService {

	@Autowired
	protected RoleRepo roleRepo;

	@Override
    @Transactional
	public Group addUser(final String groupId, final String userId) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(userId, "用不能为空");

		final Group groupEntity = groupRepo.getOne(groupId);
		final User userEntity = userRepo.getOne(userId);

        Assert.notNull(groupEntity, "组不存在");
        Assert.notNull(userEntity, "用户不存在");

		if (groupEntity.getUsers() == null) {
			groupEntity.setUsers(new HashSet<User>());
		}
		groupEntity.getUsers().add(userEntity);
		groupRepo.saveAndFlush(groupEntity);
		return groupEntity;
	}

	@Override
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
	public Group removeUser(final String groupId, final String userId) {
        return removeuserimpl(groupId, userId);
	}

    /**
     * @param groupId
     * @param userId
     * @return
     */
    private Group removeuserimpl(final String groupId, final String userId) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(userId, "用不能为空");

		final Group groupEntity = groupRepo.getOne(groupId);
		final User userEntity = userRepo.getOne(userId);

        Assert.notNull(groupEntity, "组不存在");
        Assert.notNull(userEntity, "用户不存在");

		if (groupEntity.getUsers() == null) {
			groupEntity.setUsers(new HashSet<User>());
		}

		groupEntity.getUsers().remove(userEntity);
		groupRepo.saveAndFlush(groupEntity);
		return groupEntity;
    }

	@Override
    @Transactional
	public Group addRole(final String groupId, final String roleId) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(roleId, "角色不能为空");

		final Group groupEntity = groupRepo.getOne(groupId);
		final Role roleEntity = roleRepo.getOne(roleId);

        Assert.notNull(groupEntity, "组不存在");
        Assert.notNull(roleEntity, "角色不存在");

		if (roleEntity.getGroups() == null) {
			roleEntity.setGroups(new HashSet<Group>());
		}
		roleEntity.getGroups().add(groupEntity);
		roleRepo.saveAndFlush(roleEntity);
		return groupEntity;
	}

	@Override
    @Transactional
	public Group removeRole(final String groupId, final String roleId) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(roleId, "角色不能为空");

		final Group groupEntity = groupRepo.getOne(groupId);
		final Role roleEntity = roleRepo.getOne(roleId);

        Assert.notNull(groupEntity, "组不存在");
        Assert.notNull(roleEntity, "角色不存在");

		if (roleEntity.getGroups() == null) {
            throw new RuntimeException("角色不存在.");
		}
		roleEntity.getGroups().remove(groupEntity);
		roleRepo.saveAndFlush(roleEntity);
		return groupEntity;
	}

	@Override
    @Transactional
	public Page<Group> findByUserPage(final User user, final Pageable page) {
        Assert.notNull(user, "用户不能为空");
        Assert.notNull(page, "分页不能为空");

		return groupRepo.findByUser(user, page);
	}

	@Override
    @Transactional
	public Page<Group> findAll(final Pageable page) {
        Assert.notNull(page, "分页不能为空");

		final Page<Group> pages = groupRepo.findAll(page);
		return pages;
	}

	@Override
    @Transactional
	public Group addUserByName(final String groupId, final String username) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(username, "用户名不能为空");

		final User userEntity = userRepo.findByUsername(username);

        Assert.notNull(userEntity, "用户不存在");

		return addUser(groupId, userEntity.getId());
	}

	@Override
    @Transactional
	public Group removeUserByName(final String groupId, final String username) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(username, "用户名不能为空");

		final User userEntity = userRepo.findByUsername(username);

        Assert.notNull(userEntity, "用户不存在");

		return removeUser(groupId, userEntity.getId());
	}

	@Override
    @Transactional
	public Group addRoleByName(final String groupId, final String rolename) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(rolename, "角色名不能为空");

		final Role role = roleRepo.findByRolename(rolename);

        Assert.notNull(role, "角色不存在");

		return addRole(groupId, role.getId());
	}

	@Override
    @Transactional
	public Group removeRoleByName(final String groupId, final String name) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(name, "角色名不能为空");

		final Role role = roleRepo.findByRolename(name);

        Assert.notNull(role, "角色不存在");

		return removeRole(groupId, role.getId());
	}

	@Override
    @Transactional
	public Page<User> findUserByGroupId(final String groupId,
			final Pageable page) {
        Assert.notNull(groupId, "组不能为空");
        Assert.notNull(page, " 分页不能为空");
		return groupRepo.findUsersByGroupId(groupId.trim(), page);
	}

	@Override
    @Transactional
	public Collection<Group> findAllByUser(final User user) {
        Assert.notNull(user, "用户不能为空");
		return groupRepo.findAllByUser(user);
	}

	@Override
    @Transactional
	public Collection<Group> findAllByUserId(final String userId) {
        Assert.notNull(userId, "用户不能为空");
		return groupRepo.findAllByUserId(userId);
	}

	@Override
    @Transactional
	public Group findByName(String name) {
		return groupRepo.findByName(name);
	}

}
