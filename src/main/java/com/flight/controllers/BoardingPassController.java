package com.flight.controllers;

import com.flight.entity.BoardingPass;
import com.flight.service.BoardingPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardingPassController {
    @Autowired
    private BoardingPassService boardingPassService;

    @PostMapping("/boardingPass")
    public BoardingPass saveBoardingPass(@RequestBody BoardingPass boardingPass){
        return boardingPassService.save(boardingPass);
    }


    @GetMapping("/boardingPass")
    public List<BoardingPass> getBoardingPass(){
        return boardingPassService.getBoardingPass();
    }
    @GetMapping("/boardingPass/{id}")
    public BoardingPass getBoardingPass(@PathVariable("id") long id){
        return boardingPassService.getBoardingPassById(id);
    }
}
