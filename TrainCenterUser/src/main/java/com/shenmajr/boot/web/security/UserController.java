package com.shenmajr.boot.web.security;

import java.util.Collection;
import java.util.List;
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
import com.shenmajr.boot.domain.security.Role;
import com.shenmajr.boot.domain.security.User;
import com.shenmajr.boot.service.security.GroupService;
import com.shenmajr.boot.service.security.RoleService;
import com.shenmajr.boot.service.security.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final int PAGE_MIN = 0;
	private static final int PAGE_MAX = 20;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private RoleService roleService;

	// FIXME 返回 正确的http 状态

	@RequestMapping(value = "", method = { RequestMethod.POST })
	public String create(final User user) {
		final User userEntity = userService.create(user);
		return "redirect:" + userEntity.getId();
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public String get(@PathVariable final String id,
			final Map<String, Object> model) {
		final User userEntity = userService.get(id);
		Collection<Group> groups = groupService.findAllByUser(userEntity);
		List<Role> roles = roleService.findAllByUserId(id);

		model.put("user", userEntity);
		model.put("groups", groups);
		model.put("roles", roles);

		return "user/info";
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public String update(@PathVariable final String id, final User user,
			final Map<String, Object> model) {
		user.setId(id);
		final User userEntity = userService.update(user);
		return "redirect:../" + userEntity.getId();
	}

	@RequestMapping(value = "/create", method = { RequestMethod.GET })
	public String createPage() {
		return "user/create";
	}

	@RequestMapping(value = "/{id}/pages/update", method = { RequestMethod.GET })
	public String updatePage(@PathVariable final String id,
			final Map<String, Object> model) {
		final User userEntity = userService.get(id);
		model.put("user", userEntity);
		return "user/update";
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String getAll(
			@RequestParam(defaultValue = "0", required = false) final Integer page,
			@RequestParam(defaultValue = "20", required = false) final Integer pagesize,
			final Map<String, Object> model) {

		final Page<User> pages = userService.findAll(new PageRequest(
				page < PAGE_MIN ? 0 : page, pagesize > PAGE_MAX ? PAGE_MAX
						: pagesize));

		model.put("page", pages);
		model.put("users", pages.getContent());
		return "user/list";
	}
}
