package com.flight.controllers;

import com.flight.entity.Plane;
import com.flight.entity.Search;
import com.flight.entity.User;
import com.flight.service.PlaneService;
import com.flight.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class PlaneController {
    private List<Plane> searchedPlanes=null;

    @Autowired
    private UserService userService;
    @Autowired
    private PlaneService planeService;
    @ModelAttribute("plane")
    public Plane plane(){
        return new Plane();
    }
    private final Logger log= LoggerFactory.getLogger(PlaneController.class);

    @ModelAttribute("search")
    public Search search(){
        return new Search();
    }

    @GetMapping("/insertFlight")
    public String insertFlight(){
        return "insertFlight";
    }
    @PostMapping("/insertFlight")
    public String saveFlight(@ModelAttribute("plane") Plane plane){
        try{
            SecurityContext securityContext = SecurityContextHolder.getContext();
            String username= securityContext.getAuthentication().getName();
            System.out.println(username);
            User user=userService.findByEmailId(username);
            planeService.savePlane(plane);
            return "redirect:/insertFlight?success";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }
    @GetMapping("/searchFlights")
    public String searchFlight(){
        return "searchFlight";
    }
    @GetMapping("/showFlights")
    public String searchFlightsGet(Model model){
        model.addAttribute("listOfSearchedFlights",searchedPlanes);
        System.out.println(searchedPlanes.toString());
        return "showFlights";
    }
    @GetMapping("/showAllFlights")
    public String showAllFlights(Model model){
        List<Plane> list=planeService.getPlanes();
        model.addAttribute("listAllFlights",list);
        return "showAllFlights";
    }
    @PostMapping("/searchFlights")
    public String searchFlightsPost(@ModelAttribute("search") Search search,Model model){
        try{

            String source=search.getSource();
            String destination=search.getDestination();
            Date date=search.getDate();
            searchedPlanes=planeService.searchPlanes(source,destination,date);

            return "redirect:/showFlights";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
            //searchFlightsGet(list,model);
            //System.out.println(list.toString());
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }
    @PostMapping("/planes")
    public ResponseEntity<Plane> savePlane(@Valid @RequestBody Plane plane){
        try{
            log.info("Inside savePlane of PlaneController");
            Plane plane1= planeService.savePlane(plane);
            return ResponseEntity.status(HttpStatus.OK).body(plane1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/planes/{id}")
    public ResponseEntity<Plane> editPlane(@PathVariable("id") long planeId, @RequestBody Plane plane){

        try{
            log.info("Inside editPlane of PlaneController");
            Plane plane1=planeService.updatePlane(planeId,plane);
            return ResponseEntity.status(HttpStatus.OK).body(plane1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping ("/deleteFlight/{id}")
    public String deletePlane(@PathVariable("id") long planeId){

        try{
            //log.info("Inside delete User of UserController");
            planeService.deletePlaneById(planeId);
            //return ResponseEntity.status(HttpStatus.OK).build();
            return "redirect:/showAllFlights?successDelete";
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return "Bad Request";
        }
    }
    @GetMapping("/editFlight/{id}")
    public String editPlane(@PathVariable("id") long planeId,Model model){
        try{
            //log.info("Inside delete User of UserController");
            Plane plane=planeService.findPlaneById(planeId);
            model.addAttribute("editPlane",plane);
            //return ResponseEntity.status(HttpStatus.OK).build();
            return "editFlightDetails";
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return "Bad Request";
        }
    }
    @PostMapping("/editFlight/{id}")
    public String updateFlight(@PathVariable long id,@ModelAttribute("plane") Plane plane,Model model){
        try{
            Plane plane1=planeService.findPlaneById(id);

        if(Objects.nonNull(plane.getAirline()) && !"".equals(plane.getAirline())){
            plane1.setAirline(plane.getAirline());
        }
        if(Objects.nonNull(plane.getSource()) && !"".equals(plane.getSource())){
            plane1.setSource(plane.getSource());
        }
        if(Objects.nonNull(plane.getDestination()) && !"".equals(plane.getDestination())){
            plane1.setDestination(plane.getDestination());
        }
        if(Objects.nonNull(plane.getDate()) ){
            plane1.setDate(plane.getDate());
        }
            if(Objects.nonNull(plane.getArrival()) && !"".equals(plane.getArrival())){
                plane1.setArrival(plane.getArrival());
            }
            if(Objects.nonNull(plane.getDeparture()) && !"".equals(plane.getDeparture())){
                plane1.setDeparture(plane.getDeparture());
            }
            if(Objects.nonNull(plane.getDuration()) && !"".equals(plane.getDuration())){
                plane1.setDuration(plane.getDuration());
            }
            if(plane.getPrice()!=0.0){
                plane1.setPrice(plane.getPrice());
            }
        planeService.savePlane(plane1);


            return "redirect:/showAllFlights?sucessEdit";
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return "Bad Request";
        }
    }
//    @GetMapping("/planes")
//    public ResponseEntity<List<Plane>>getPlanes(){
//
//        try{
//            log.info("Inside getUser of UserController");
//            List<Plane> list=planeService.getPlanes();
//            return ResponseEntity.status(HttpStatus.OK).body(list);
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
@GetMapping("/planes")
public String getUsers(Model model){

    try{

        List<Plane> list=planeService.getPlanes();
        model.addAttribute("planes",list);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username= securityContext.getAuthentication().getName();
        System.out.println(username);


        return "searchFlight";
    }catch (Exception e){
        log.error(e.getMessage(), e);
        return "Bad Request";
    }

}
    @GetMapping("/planes/{id}")
    public ResponseEntity<Plane> getPlaneById(@PathVariable("id") long planeId){
        try{
            log.info("Inside getUserByID of UserController");
            Plane plane1=planeService.findPlaneById(planeId);
            return ResponseEntity.status(HttpStatus.OK).body(plane1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
