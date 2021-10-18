package web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping(value = "")
	public String UserPage(Authentication auth, ModelMap model) {
		User user = (User) auth.getPrincipal();
		List<String> messages = new ArrayList<>();
		messages.add(String.format("Hello 11122223334444 user %s!", user.getFirstName()));
		messages.add("Your id: " + user.getId());
		messages.add("First name: " + user.getFirstName());
		messages.add("Last name: " + user.getLastName());
		messages.add("E-mail: " + user.getEmail());
		messages.add("Added/updated: " + user.getTimeOfAdd());
		StringBuilder sb = new StringBuilder();
		for (Role role: user.getRoles()) {
			sb.append(role.getName()).append(", ");
		}
		messages.add("Roles: " + sb.toString());
		model.addAttribute("messages", messages);
		return "user";
	}


}