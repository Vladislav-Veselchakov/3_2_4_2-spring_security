package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.Role;
import web.model.User;
import web.model.User_role;
import web.service.RoleService;
import web.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class AdminCtrl {
    @PersistenceContext
    EntityManager entityManager;

    private UserService service;
    private RoleService roleService;

    public AdminCtrl(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String getUsers(ModelMap model, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

//        return "redirect:cars?count=-1";
        List<User> users = service.getUsers();
        model.addAttribute("users", users);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);

        model.addAttribute("result001", "result001");


        List<User_role> userRole = (List<User_role>) entityManager.createNativeQuery( "SELECT user_id, role_id FROM user_role").getResultList();
        // results.get(0)[0]
        model.addAttribute("userRole", userRole);

        return "admin";
    }

}
