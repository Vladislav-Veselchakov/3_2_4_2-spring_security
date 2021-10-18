package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("/admin")
public class AddRoleCrtl {


    private RoleService service;
    public AddRoleCrtl(RoleService service) {
        this.service = service;
    }

    @GetMapping(value = "/addRole",  produces = {"application/xml; charset=UTF-8"})
    String addRolePage(ModelMap model, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Role role = new Role();
        model.addAttribute("role", role);
        return "addRole";
    }

    @PostMapping(value = "/addRole",  produces = {"application/xml; charset=UTF-8"})
    public String addRole(@ModelAttribute("role") Role role, ModelMap model, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException  {
        response.setContentType("text/html;charset=UTF-8");

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

//        String uFirst = new String(user.getFirstName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        String uLast = new String(user.getLastName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        String uMail = new String(user.getEmail().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        user.setFirstName(uFirst);
//        user.setLastName(uLast);
//        user.setEmail(uMail);

        service.add(role);

        return "redirect:/";
    }




}
