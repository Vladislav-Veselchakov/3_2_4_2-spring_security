package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.Role;
import web.model.User;
import web.model.User_role;
import web.service.RoleService;
import web.service.UserService;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AllUsersController {
    @PersistenceContext
    EntityManager entityManager;



    private UserService service;
    private RoleService roleService;

    public AllUsersController(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
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
        int a = 1;
        model.addAttribute("userRole", userRole);

        return "index";
    }



// url: http://localhost:8080/manager/html
//	Но перед этим пришлось в файле tomcat-users.xml вписать:
//<role rolename="manager-gui"/>
//<user username="tomcat" password="s3cret" roles="manager-gui"/>
// on site
// https://helpcontext.ru/questions/25999/intellij-tomcat-pokazyvaet-404-pri-zapuske

}
