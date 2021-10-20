package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
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
        Role role = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :roleName", Role.class).setParameter("roleName", roleName).getSingleResult();

        DateFormat df = new SimpleDateFormat("HH:mm:ss dd-MM-YYYY");
        user.setTimeOfAdd(df.format((new GregorianCalendar()).getTime()));
        User user1 = userService.getUserById(user.getId());
        Set<Role> roles = userService.getRoles(user.getId());
        roles.add(role);
        user.setRoles(roles);

        userService.update(user);
        return "redirect:/admin";
    }

}
