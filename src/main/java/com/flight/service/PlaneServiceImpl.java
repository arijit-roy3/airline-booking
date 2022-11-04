package com.flight.service;

import com.flight.entity.Plane;
import com.flight.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PlaneServiceImpl implements PlaneService{
    @Autowired
    PlaneRepository planeRepository;
    @Override
    public List<Plane> getPlanes() {
        return planeRepository.findAll();
    }

    @Override
    public Plane findPlaneById(long id) {
        return planeRepository.findByPlaneId(id);
    }

    @Override
    public Plane savePlane(Plane plane) {
        return planeRepository.save(plane);
    }

    @Override
    public void deletePlaneById(long planeId) {
        planeRepository.deleteById(planeId);
    }

//    @Override
//    public Plane updatePlane(long planeId, Plane plane) {
//        Plane plane1=planeRepository.findByPlaneId(planeId);
//        if(Objects.nonNull(plane.getAirline()) && !"".equals(plane.getAirline())){
//            plane1.setAirline(plane.getAirline());
//        }
//        if(Objects.nonNull(plane.getFrom_airport()) && !"".equals(plane.getFrom_airport())){
//            plane1.setFrom_airport(plane.getFrom_airport());
//        }
//        if(Objects.nonNull(plane.getTo_airport()) && !"".equals(plane.getTo_airport())){
//            plane1.setTo_airport(plane.getTo_airport());
//        }
//        if(Objects.nonNull(plane.getDate_journey()) ){
//            plane1.setDate_journey(plane.getDate_journey());
//        }
//        planeRepository.save(plane1);
//        return plane1;
//    }
    @Override
    public Plane updatePlane(long planeId, Plane plane){
        return null;
    }

    @Override
    public List<Plane> searchPlanes(String source, String destination, Date date) {
        return  planeRepository.findBySourceAndDestinationAndDate(source,destination,date);
    }
}
