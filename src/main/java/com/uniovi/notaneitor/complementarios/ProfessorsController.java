package com.uniovi.notaneitor.complementarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfessorsController {

    @Autowired
    private ProfessorsService professorsService;



    @RequestMapping("professor/list" )
    public String getList(Model model) {
        return professorsService.getProfessorsList().toString();
        //model.addAttribute("professorList",professorsService.getProfessorsList());
        //return "listProfessors";
    }

    @RequestMapping(value ="professor/add")
    public String addProfessor(@ModelAttribute Professor professor) {

        professorsService.addProfessor(professor);
        return "Profesor añadido correctamente";
        //return "redirect:/professor/list";
    }



    @RequestMapping("professor/details/{id}")
    public Professor getDetail(@PathVariable Long id) {
        return professorsService.getProfessor(id);
    }

    @RequestMapping("professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorsService.deleteProfessor(id);
        return "Profesor eliminado correctamente";
    }

    @RequestMapping("professor/edit")
    public String editProfessor(@ModelAttribute Professor professor) {

        professorsService.editProfessor(professor);
        return "Profesor editado correctamente";
    }
}
