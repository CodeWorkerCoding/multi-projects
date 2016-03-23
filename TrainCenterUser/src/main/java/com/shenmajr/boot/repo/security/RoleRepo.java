package com.shenmajr.boot.repo.security;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.Role;

public interface RoleRepo extends JpaRepository<Role, String> {

	@Query("SELECT r FROM Role r JOIN r.groups gs WHERE gs=:group ORDER By r.createdTime DESC")
	Page<Role> findByGroup(@Param(value = "group") Group group, Pageable page);

	@Query("SELECT r FROM Role r JOIN r.groups gs WHERE gs.id=:groupId ORDER By r.createdTime DESC")
	Page<Role> findByGroupId(@Param(value = "groupId") String groupId,
			Pageable page);

	@Query("SELECT r FROM Role r JOIN r.groups gs WHERE gs.name=:groupName ORDER By r.createdTime DESC")
	Page<Role> findByGroupName(@Param(value = "groupName") String groupName,
			Pageable page);

	Role findByRolename(String rolename);

	@Query("SELECT r FROM Role r JOIN r.groups rgs JOIN rgs.users rgus WHERE rgus.id=:userId")
	List<Role> findAllByUserId(@Param(value = "userId") String userId);

	@Query("SELECT r FROM Role r JOIN r.groups rgs JOIN rgs.users rgus WHERE rgus.username=:username")
	List<Role> findAllByUserName(@Param(value = "username") String username);

	@Query("SELECT rgs FROM Role r JOIN r.groups rgs WHERE r.id=:roleId ORDER by rgs.createdTime DESC")
	Page<Group> findGroupByRoleId(@Param(value = "roleId") String roleId,
			Pageable page);

}
