package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AllUsersController {

    private UserService service;

    public AllUsersController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String getUsers(ModelMap model, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

//        return "redirect:cars?count=-1";
        List<User> users = service.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());

        model.addAttribute("input2", "one tnwo thhhhhreee");
        return "index";
    }



// url: http://localhost:8080/manager/html
//	Но перед этим пришлось в файле tomcat-users.xml вписать:
//<role rolename="manager-gui"/>
//<user username="tomcat" password="s3cret" roles="manager-gui"/>
// on site
// https://helpcontext.ru/questions/25999/intellij-tomcat-pokazyvaet-404-pri-zapuske

}
