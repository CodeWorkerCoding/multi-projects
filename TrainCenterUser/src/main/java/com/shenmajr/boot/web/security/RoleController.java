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

import com.shenmajr.boot.domain.security.Role;
import com.shenmajr.boot.service.security.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	private static final int PAGE_MIN = 0;
	private static final int PAGE_MAX = 20;
	@Autowired
	RoleService roleService;

	// FIXME 返回 正确的http 状态
	@RequestMapping(value = "", method = { RequestMethod.POST })
	public String create(final Role role) {
		final Role rEntity = roleService.create(role);
		return "redirect:/roles/" + rEntity.getId();
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public String update(@PathVariable final String Id, final Role role,
			final Map<String, Object> model) {
		role.setId(Id);
		final Role rEntity = roleService.update(role);
		return "redirect:" + rEntity.getId();
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public String get(@PathVariable final String id,
			final Map<String, Object> model) {
		final Role rEntity = roleService.get(id);
		model.put("role", rEntity);
		return "role/info";
	}

	@RequestMapping(value = "/create", method = { RequestMethod.GET })
	public String createPage() {
		return "role/create";
	}

	@RequestMapping(value = "/{id}/update", method = { RequestMethod.GET })
	public String updatePage(@PathVariable final String id,
			final Map<String, Object> model) {
		final Role rEntity = roleService.get(id);
		model.put("role", rEntity);
		return "role/create";
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String findAll(
			@RequestParam(defaultValue = "0", required = false) final Integer page,
			final @RequestParam(defaultValue = "20", required = false) Integer pagesize,
			final Map<String, Object> model) {
		final Page<Role> result = roleService.findAll(new PageRequest(
				page < PAGE_MIN ? 0 : page, pagesize > PAGE_MAX ? PAGE_MAX
						: pagesize));
		model.put("roles", result.getContent());
		model.put("page", result);
		return "role/list";
	}
}
