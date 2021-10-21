package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class EditController {
    @PersistenceContext
    EntityManager entityManager;

    private UserService userService;
    public EditController(UserService service) {
        this.userService = service;
    }

    @GetMapping(value = "/edit")
    String editPage(@RequestParam Long id, ModelMap model) {
        User user = userService.getUserById(id);
        // user.roles.iterator().next().getAuthority()
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    String editUser(@ModelAttribute("user") User user, ModelMap model, @ModelAttribute("roleName") String roleName) {
//        String uFirst = new String(user.getFirstName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        String uLast = new String(user.getLastName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        String uMail = new String(user.getEmail().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        user.setFirstName(uFirst);
//        user.setLastName(uLast);
//        user.setEmail(uMail);

        userService.setRoleByName(user, roleName);
        userService.setModified(user, new GregorianCalendar().getTime());
        userService.update(user);
        return "redirect:/admin";
    }

}
