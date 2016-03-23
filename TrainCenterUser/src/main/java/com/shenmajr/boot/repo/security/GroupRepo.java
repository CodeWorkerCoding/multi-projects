package com.shenmajr.boot.repo.security;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.User;

public interface GroupRepo extends JpaRepository<Group, String> {
	
	@Query(value = "SELECT g FROM Group g JOIN g.users u WHERE u =:user ORDER BY g.createdTime DESC")
	Page<Group> findByUser(@Param(value = "user") User user, Pageable page);

	@Query(value = "SELECT g FROM Group g JOIN g.users u WHERE u.id =:userId ORDER BY g.createdTime DESC")
	Page<Group> findByUserId(@Param(value = "userId") String userId,
			Pageable page);

	@Query(value = "SELECT g FROM Group g JOIN g.users u WHERE u.name =:userName ORDER BY g.createdTime DESC")
	Page<Group> findByUserName(@Param(value = "userName") String userName,
			Pageable page);

	Group findByName(String name);

	@Query(value = "SELECT g FROM Group g JOIN g.users u WHERE u =:user")
	List<Group> findAllByUser(@Param(value = "user") User user);

	@Query(value = "SELECT g FROM Group g JOIN g.users u WHERE u.id =:userId")
	List<Group> findAllByUserId(@Param(value = "userId") String userId);

	@Query(value = "SELECT g FROM Group g JOIN g.users u WHERE u.username =:username")
	List<Group> findAllByUsername(@Param(value = "username") String username);

	@Query("SELECT gus FROM Group g JOIN g.users gus WHERE g.id=:groupId ORDER BY gus.createdTime DESC")
	Page<User> findUsersByGroupId(@Param("groupId") String groupId,
			Pageable page);

	@Query("SELECT gus FROM Group g JOIN g.users gus WHERE g.groupname=:groupname ORDER BY gus.createdTime DESC")
	Page<User> findUsersByGroupname(@Param("groupname") String groupname,
			Pageable page);
}
