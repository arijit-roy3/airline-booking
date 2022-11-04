package com.flight.repository;

import com.flight.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PlaneRepository extends JpaRepository<Plane,Long> {
    public Plane findByPlaneId(long id);

    List<Plane> findBySourceAndDestinationAndDate(String source, String destination, Date date);
}
