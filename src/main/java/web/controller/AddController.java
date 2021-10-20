package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Controller
public class AddController {
    private UserService userService;
    public AddController(UserService service) {
        this.userService = service;
    }

    @GetMapping(value = "/admin/addUser")
    String addPage(ModelMap model) {
        User usr = new User();
        usr.setFirstName("семь из сет");
        model.addAttribute("user", usr);
        return "addUser";
    }

    @PostMapping(value = "/admin/addUser",  produces = {"application/xml; charset=UTF-8"})
    public String addUser(@ModelAttribute("user") User user, ModelMap model) {

        DateFormat df = new SimpleDateFormat("HH:mm:ss dd-MM-YYYY");
        user.setTimeOfAdd(df.format((new GregorianCalendar()).getTime()));
        userService.add(user);
        return "redirect:/admin";
    }
}
