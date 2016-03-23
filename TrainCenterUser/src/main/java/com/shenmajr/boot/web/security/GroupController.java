package com.shenmajr.boot.web.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shenmajr.boot.domain.security.Group;
import com.shenmajr.boot.domain.security.User;
import com.shenmajr.boot.service.security.GroupService;
import com.shenmajr.boot.service.security.RoleService;
import com.shenmajr.boot.service.security.UserService;

@Controller
@RequestMapping("/group")
public class GroupController {

	private static final int PAGE_MIN = 0;
	private static final int PAGE_MAX = 20;
	@Autowired
	GroupService groupService;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	// FIXME 返回 正确的http 状态

	@RequestMapping(value = "", method = { RequestMethod.POST })
	public String create(final Group group) {
		final Group gEntity = groupService.create(group);
		return "redirect:" + gEntity.getId();
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public String get(@PathVariable final String id,
			final Map<String, Object> model) {
		final Group gEntity = groupService.get(id);
		model.put("group", gEntity);
		return "group/info";
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public String update(@PathVariable final String id, final Group group,
			final Map<String, Object> model) {
		group.setId(id);
		final Group gEntity = groupService.update(group);
		return "redirect:" + gEntity.getId();
	}

	@RequestMapping(value = "/pages/create", method = { RequestMethod.GET })
	public String createPage() {
		return "group/create";
	}

	@RequestMapping(value = "/{id}/pages/update", method = { RequestMethod.GET })
	public String updatePage(@PathVariable final String id,
			final Map<String, Object> model) {
		final Group gEntity = groupService.get(id);
		model.put("group", gEntity);
		return "group/update";
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String findAll(
			@RequestParam(defaultValue = "0", required = false) final Integer page,
			final @RequestParam(defaultValue = "20", required = false) Integer pagesize,
			final Map<String, Object> model) {
		final Page<Group> result = groupService.findAll(new PageRequest(
				page < PAGE_MIN ? 0 : page, pagesize > PAGE_MAX ? PAGE_MAX
						: pagesize));
		model.put("groups", result.getContent());
		model.put("page", result);
		return "group/list";
	}

	@RequestMapping(value = "/{groupId}/users", method = { RequestMethod.GET })
	public String findGroupUsers(
			@PathVariable final String groupId,
			@RequestParam(defaultValue = "0", required = false) final Integer page,
			final @RequestParam(defaultValue = "20", required = false) Integer pagesize,
			final Map<String, Object> model) {
		final Page<User> result = groupService.findUserByGroupId(groupId,
				new PageRequest(page < PAGE_MIN ? 0 : page,
						pagesize > PAGE_MAX ? PAGE_MAX : pagesize));
		Group group = groupService.get(groupId);
		model.put("users", result.getContent());
		model.put("page", result);
		model.put("group", group);
		return "group/userlist";
	}

	@RequestMapping(value = "/{groupId}/users", method = { RequestMethod.POST })
	public String addUser(@PathVariable final String groupId, final String name) {
		groupService.addUserByName(groupId, name);
		return "redirect:users";
	}

	@RequestMapping(value = "/{groupId}/users", method = { RequestMethod.DELETE })
	public String removeUser(@PathVariable final String groupId,
			final String name) {
		groupService.removeUserByName(groupId, name);
		return "redirect:users";
	}

}
