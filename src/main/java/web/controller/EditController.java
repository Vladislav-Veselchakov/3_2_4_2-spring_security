package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class EditController {
    private UserService service;
    public EditController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/edit")
    String editPage(@RequestParam Long id, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        User user = service.getUserById(id);
        // user.roles.iterator().next().getAuthority()
        model.addAttribute("user", user);

        return "editUser";
    }

    @PostMapping(value = "/editUser")
    String editUser(@ModelAttribute("user") User user, ModelMap model, @ModelAttribute("role") String role, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

//        String uFirst = new String(user.getFirstName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        String uLast = new String(user.getLastName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        String uMail = new String(user.getEmail().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        user.setFirstName(uFirst);
//        user.setLastName(uLast);
//        user.setEmail(uMail);


        DateFormat df = new SimpleDateFormat("HH:mm:ss dd-MM-YYYY");
        user.setTimeOfAdd(df.format((new GregorianCalendar()).getTime()));
        Set<Role> roles = user.getRoles();
        roles.add(new Role(role));
        user.setRoles(roles);

        service.update(user);
        return "redirect:/admin";
    }

}
