package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.services.MarksService;
import com.uniovi.notaneitor.services.RolesService;
import com.uniovi.notaneitor.services.SecurityService;
import com.uniovi.notaneitor.validators.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.services.UsersService;

import javax.naming.Binding;

@Controller
public class UsersController {

    private final UsersService usersService;
    private final SecurityService securityService;
    private final SignUpFormValidator signUpFormValidator;
    private final RolesService rolesService;
    private final MarksService marksService;

    @Autowired
    public UsersController(UsersService usersService, SecurityService securityService,
                           SignUpFormValidator signUpFormValidator, RolesService rolesService, MarksService marksService) {
        this.usersService = usersService;
        this.securityService = securityService;
        this.signUpFormValidator = signUpFormValidator;
        this.rolesService = rolesService;
        this.marksService = marksService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {

        signUpFormValidator.validate(user,result);
        if (result.hasErrors()) {
            return "user/signup";
        }
        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);
        securityService.autoLogin(user.getDni(), user.getPasswordConfirm());
        return "redirect:/home";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupView(Model model) {
        model.addAttribute("user", new User());
        return "user/signup";
    }


    @RequestMapping("/user/list/update")
    public String updateList(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        return "user/list :: usersTable";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/home" , method = RequestMethod.GET)
    public String home(Model model, Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Page<Mark> marks ;

        if (auth != null && auth.getName() != null) {
            String dni = auth.getName();
            User user = usersService.getUserByDni(dni);

            marks = marksService.getMarksForUser(pageable,user);
            model.addAttribute("marksList", marks.getContent());
            model.addAttribute("page", marks);
        } else {
            model.addAttribute("error", "No hay usuario autenticado.");
        }

        return "user/home";
    }


    @RequestMapping("/user/list")
    public String getListado(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        return "user/list";
    }

    @RequestMapping(value = "/user/add")
    public String getUser(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        model.addAttribute("rolesList", rolesService.getRoles());
        return "user/add";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String setUser(@ModelAttribute User user) {
        usersService.addUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/user/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getUser(id));
        return "user/details";
    }

    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        usersService.deleteUser(id);
        return "redirect:/user/list";
    }




    @RequestMapping(value = "/user/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        User user = usersService.getUser(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
    public String setEdit(@ModelAttribute User user, @PathVariable Long id) {
        User ogUser = usersService.getUser(id);
        ogUser.setDni(user.getDni());
        ogUser.setName(user.getName());
        ogUser.setLastName(user.getLastName());
        usersService.addUser(ogUser);
        return "redirect:/user/list";
    }
}
