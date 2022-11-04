package com.flight.service;

import com.flight.entity.Booking;
import com.flight.entity.User;
import com.flight.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findBookingById(long id) {
        return bookingRepository.findByBookingId(id);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking updateBooking(long id, Booking booking) {
        return null;
    }

//    @Override
//    public List<Booking> findByUser(User user) {
//        return bookingRepository.findByUser(user);
//    }
}
