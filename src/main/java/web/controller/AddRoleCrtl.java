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

    @GetMapping(value = "/addRole",  produces = {"application/xml; charset=UTF-8"})
    String addRolePage(ModelMap model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "addRole";
    }

    @PostMapping(value = "/addRole",  produces = {"application/xml; charset=UTF-8"})
    public String addRole(@ModelAttribute("role") Role role, ModelMap model)  {
        roleService.add(role);
        return "redirect:/";
    }




}
