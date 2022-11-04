package com.flight.controllers;

import com.flight.entity.Booking;
import com.flight.entity.Plane;
import com.flight.entity.User;
import com.flight.service.BookingService;
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
import java.util.List;
import java.util.Objects;

@Controller
public class BookingController {

    @Autowired
    PlaneService planeService;
    private long planeId;
    @ModelAttribute("booking")
    public Booking booking(){
        return new Booking();
    }
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;
    private final Logger log= LoggerFactory.getLogger(BookingController.class);

//    @PostMapping("/booking")
//    public ResponseEntity<Booking> saveBooking(@Valid @RequestBody Booking booking){
//        try{
//            log.info("Inside saveBooking of BookingController");
//            Booking booking1= bookingService.saveBooking(booking);
//            return ResponseEntity.status(HttpStatus.OK).body(booking1);
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
    @GetMapping("/bookFlight/{id}")
    public String bookingController(@PathVariable("id") long planeId){
        try{
            this.planeId=planeId;
            System.out.println(planeId);
            return "bookFlightNow";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }
    @PostMapping("/bookFlight")
    public String bookingSave(@ModelAttribute("booking") Booking booking){
        try{
            SecurityContext securityContext = SecurityContextHolder.getContext();
            String username= securityContext.getAuthentication().getName();
            System.out.println(username);
            User user=userService.findByEmailId(username);
            booking.setPass(0);

            //booking.setUser(user);
            Plane plane=planeService.findPlaneById(planeId);
            booking.setDateBooking(plane.getDate());
            booking.setDestinationBooking(plane.getDestination());
            booking.setSourceBooking(plane.getSource());
            booking.setUsername(username);
            booking.setPlaneId(planeId);
            bookingService.saveBooking(booking);
            return "redirect:/bookFlight/"+planeId+"?success";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }
    @PostMapping("/saveBooking")
    public String bookingController(@ModelAttribute("booking") Booking booking){
        try{
            bookingService.saveBooking(booking);
            return "redirect:/index";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }

    @GetMapping("/viewBookings")
    public String viewBookings(Model model){
        try{
            System.out.println("Inside view bookings");
            SecurityContext securityContext = SecurityContextHolder.getContext();
            String username= securityContext.getAuthentication().getName();
            System.out.println(username);
            //User user=userService.findByEmailId(username);
            //long id=user.getUserId();
           // System.out.println(user);
            List<Booking> listBookings=bookingService.getBookings();
            for(Booking booking:listBookings){
                if(!booking.getUsername().equals(username)){
                    listBookings.remove(booking);
                }
            }
            //System.out.println(listBookings.toString());
            model.addAttribute("listBookings",listBookings);
            return "showUserBookings";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }

    @GetMapping("/userBookings/delete/{id}")
    public String deleteUserBooking(@PathVariable("id") long id){
        try{
            bookingService.deleteBooking(id);
            return "showUserBookings";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }

    @GetMapping("/userBookings/edit/{id}")
    public String rescheduleBooking(@PathVariable("id") long id,Model model){
        try{
            model.addAttribute("editBooking",bookingService.findBookingById(id));
            return "rescheduleUserBooking";
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }
    @PostMapping("/userBookings/edit/{id}")
    public String updateBooking(@PathVariable Long id,
                                @ModelAttribute("editBooking") Booking booking,
                                Model model) {

        Booking booking1=bookingService.findBookingById(id);

        if(Objects.nonNull(booking.getDateBooking()) ){
            booking1.setDateBooking(booking.getDateBooking());
        }

        bookingService.saveBooking(booking1);
        return "redirect:/viewBookings?successEdit";
    }

    @GetMapping("/userBookings/boardingPass/{id}")
    public String generateBoardingPass(@PathVariable("id") long id){
        try{
            Booking booking=bookingService.findBookingById(id);
            if(booking.getPass()==1)
            return "redirect:/viewBookings/?failedBoardingPass";
            else{
                booking.setPass(1);
                bookingService.saveBooking(booking);

                return "redirect:/viewBookings?successBoardingPass";
            }
            //return ResponseEntity.status(HttpStatus.OK).body(user1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }
    @PutMapping("/booking/{id}")
    public ResponseEntity<Booking> editBooking(@PathVariable("id") long bookingId, @RequestBody Booking booking){

        try{
            log.info("Inside editBooking of bookingcontrollers");
            Booking booking1=bookingService.updateBooking(bookingId,booking);
            return ResponseEntity.status(HttpStatus.OK).body(booking1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/userBookings/delete/{id}")
    public String deleteBooking(@PathVariable("id") long bookingId){

        try{
            log.info("Inside delete booking of bookingcontrollers");
            bookingService.deleteBooking(bookingId);
            return "redirect:/showUserBookings?deleteSuccess";
            //return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(e.getMessage(), e);
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Bad Request";
        }
    }
    @GetMapping("/booking")
    public ResponseEntity<List<Booking>> getBookings(){

        try{
            log.info("Inside getBookings of bookingcontrollers");
            List<Booking> list=bookingService.getBookings();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") long planeId){
        try{
            log.info("Inside getBookingById of bookingcontrollers");
            Booking booking1=bookingService.findBookingById(planeId);
            return ResponseEntity.status(HttpStatus.OK).body(booking1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
