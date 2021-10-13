package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

/** from vl: AllUsersController
	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

//	@GetMapping(value = "/")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getUsers(ModelMap model)  {

//        return "redirect:cars?count=-1";
		List<User> users = service.getUsers();
		model.addAttribute("users", users);
		model.addAttribute("user", new User());

		model.addAttribute("input2", "one tnwo thhhhhreee");
		return "index";
	}
*/


	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}