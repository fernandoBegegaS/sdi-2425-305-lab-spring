package com.uniovi.notaneitor.complementarios;

import com.uniovi.notaneitor.entities.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfessorsController {

    @Autowired
    private ProfessorsService professorsService;



    @RequestMapping("professor/list" )
    public String getList(Model model) {
        model.addAttribute("professorList",professorsService.getProfessorsList());
        return "complementarios/professor/list";
    }

    @RequestMapping(value ="professor/add", method = RequestMethod.POST)
    public String addProfessor(@ModelAttribute Professor professor) {

        professorsService.addProfessor(professor);
        return "redirect:/professor/list";
    }

    @RequestMapping(value ="professor/add")
    public String addProfessor() {
        return "complementarios/professor/add";
    }



    @RequestMapping("professor/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("professor",  professorsService.getProfessor(id));

        return "complementarios/professor/details";
    }

    @RequestMapping("professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {

        professorsService.deleteProfessor(id);
        return "redirect:/professor/list";
    }

    @RequestMapping("professor/edit/{id}")
    public String editProfessor(Model model,@PathVariable Long id) {

        model.addAttribute("professor", professorsService.getProfessor(id));
        return "complementarios/professor/edit";
    }

    @RequestMapping(value = "professor/edit", method = RequestMethod.POST)
    public String editProfessorSet(@ModelAttribute Professor professor) {

        professorsService.editProfessor(professor);
        return "redirect:/professor/list";
    }
}
