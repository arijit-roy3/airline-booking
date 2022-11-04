package com.flight.service;

import com.flight.entity.Plane;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface PlaneService {
    public List<Plane> getPlanes();
    public Plane findPlaneById(long id);

    public Plane savePlane(Plane plane);

    public void deletePlaneById(long planeId);

    public Plane updatePlane(long planeId, Plane plane);

    List<Plane> searchPlanes(String source, String destination, Date date);
}
