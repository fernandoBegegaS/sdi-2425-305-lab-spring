package com.uniovi.notaneitor.controllers;
import com.uniovi.notaneitor.entities.Mark;
import org.springframework.web.bind.annotation.*;


@RestController
public class MarksController {

    @RequestMapping("/mark/list")
    public String getList() {
        return "Getting List";
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
        return "added: " + mark.getDescription() +
                " with score : " + mark.getScore() +
                " id: " + mark.getId();
    }

    @RequestMapping("/mark/details")
    public String getDetail(@RequestParam Long id) {
        return "Getting Details for ID: " + id;
    }


}
