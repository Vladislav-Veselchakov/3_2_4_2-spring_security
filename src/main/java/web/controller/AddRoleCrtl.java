package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.Role;
import web.service.RoleService;

@Controller
@RequestMapping("/admin")
public class AddRoleCrtl {

    private RoleService roleService;
    public AddRoleCrtl(RoleService service) {
        this.roleService = service;
    }

    @GetMapping(value = "/addRole")
    String addRolePage(ModelMap model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "addRole";
    }

    @PostMapping(value = "/addRole")
    public String addRole(@ModelAttribute("name") String name, ModelMap model)  {
        roleService.add(new Role(name));
        return "redirect:/";
    }




}
