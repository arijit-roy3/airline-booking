package com.flight.repository;

import com.flight.entity.Booking;
import com.flight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    public Booking findByBookingId(long id);

    //List<Booking> findByUser(User user);
}
