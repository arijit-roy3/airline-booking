package com.flight.controllers;

import com.flight.entity.User;
import com.flight.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private final Logger log= LoggerFactory.getLogger(UserController.class);

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @GetMapping("/registration")
    public String saveUser(){
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") User user){
        try{
            log.info("Inside saveUser of UserController");
            User user1= userService.saveUser(user);
            return "redirect:/registration?success";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") long userId, @ModelAttribute("user") User user){

        try{
            log.info("Inside editUser of UserController");
            User user1=userService.updateUser(userId,user);
            return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long userId){

        try{
            log.info("Inside delete User of UserController");
            userService.deleteUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
//    @GetMapping("/users")
//    public ResponseEntity<List<User>>getUsers(){
//
//        try{
//            log.info("Inside getUser of UserController");
//            List<User> list=userService.getUsers();
//            return ResponseEntity.status(HttpStatus.OK).body(list);
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
@GetMapping("/users")
public String getUsers(Model model){

    try{
        log.info("Inside getUser of UserController");
        List<User> list=userService.getUsers();
        model.addAttribute("users",list);
        return "index";
    }catch (Exception e){
        log.error(e.getMessage(), e);
        return "Bad Request";
    }
}
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long userId){
        try{
            log.info("Inside getUserByID of UserController");
            User user1=userService.findUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
