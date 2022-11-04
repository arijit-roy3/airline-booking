package com.flight.service;

import com.flight.entity.Booking;
import com.flight.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    public List<Booking> getBookings();
    public Booking findBookingById(long id);

    public Booking saveBooking(Booking booking);

    public void deleteBooking(long id);

    public Booking updateBooking(long id, Booking booking);

    //List<Booking> findByUser(User user);
}
