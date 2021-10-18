package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.service.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Controller
public class DeleteRoleCtrl {
    @PersistenceContext
    EntityManager entityManager;

    private RoleService service;

    public DeleteRoleCtrl(RoleService service) {
        this.service = service;
    }
    @GetMapping(value = "/admin/deleteRole")
    @Transactional
    public String deleteRole(@RequestParam long id, RedirectAttributes attr, ModelMap model) {
        service.deleteRole(id);
        Query qry = entityManager.createNativeQuery("DELETE FROM user_role WHERE Role_id = :id");
        qry.setParameter("id", id);
        qry.executeUpdate();
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        attr.addFlashAttribute("result002", "Role deleted at " + df.format((new GregorianCalendar()).getTime()));
        return "redirect:/admin";

    }

}
