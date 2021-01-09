package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Person;
import web.model.Role;
import web.service.PersonService;

@Controller
@RequestMapping("/admin")
public class PeopleController {

    @GetMapping()
    public String getAdminPage(Model model, @AuthenticationPrincipal Person customUser
            ,@ModelAttribute("person") Person person) {
        model.addAttribute("person_v2", customUser);
        model.addAttribute("people", personService.index());
        return "admin/adminPage";
    }

    @Autowired
    @Qualifier("personServiceImp")
    private PersonService personService;
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personService.show(id));
        return "admin/show";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person, @ModelAttribute("role") Role role ) {
        personService.save(person, role);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") Long id) {
        personService.update(id, person);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long  id) {
        personService.delete(id);
        return "redirect:/admin";
    }

}