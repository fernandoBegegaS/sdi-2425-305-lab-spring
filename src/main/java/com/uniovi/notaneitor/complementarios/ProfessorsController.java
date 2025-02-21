package com.uniovi.notaneitor.complementarios;

import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.repositories.UsersRepository;
import com.uniovi.notaneitor.services.MarksService;
import com.uniovi.notaneitor.services.UsersService;
import com.uniovi.notaneitor.validators.AddMarkValidator;
import com.uniovi.notaneitor.validators.AddProfessorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfessorsController {

    @Autowired
    private ProfessorsService professorsService;

    private final UsersService usersService;


    public ProfessorsController(ProfessorsService professorsService ,
                                UsersService usersService) {
        this.professorsService = professorsService;;
        this.usersService = usersService;
    }

    @RequestMapping("professor/list" )
    public String getList(Model model) {
        model.addAttribute("professorList",professorsService.getProfessorsList());
        return "complementarios/professor/list";
    }

    @RequestMapping(value ="professor/add", method = RequestMethod.POST)
    public String addProfessor(@Validated User user, BindingResult result) {

        usersService.addUser(user);
        return "redirect:/professor/list";
    }

    @RequestMapping(value ="professor/add")
    public String addProfessor() {
        return "complementarios/professor/add";
    }



    @RequestMapping("professor/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("professor",  usersService.getUser(id));


        return "complementarios/professor/details";
    }

    @RequestMapping("professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {

        usersService.deleteUser(id);
        return "redirect:/professor/list";
    }


    @RequestMapping(value = "professor/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        User user = usersService.getUser(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequestMapping(value = "professor/edit", method = RequestMethod.POST)
    public String setEdit(@ModelAttribute User user, @PathVariable Long id) {
        User ogUser = usersService.getUser(id);
        ogUser.setDni(user.getDni());
        ogUser.setName(user.getName());
        ogUser.setLastName(user.getLastName());
        usersService.addUser(ogUser);
        return "redirect:/user/list";
    }
}
