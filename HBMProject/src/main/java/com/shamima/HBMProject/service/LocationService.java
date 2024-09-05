package com.shamima.HBMProject.service;


import com.shamima.HBMProject.entity.Location;
import com.shamima.HBMProject.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getALlLocation(){

        return locationRepository.findAll();

    }

    public  void saveLocation(Location l){
        locationRepository
                .save(l);
    }

    public  void deleteLocation(int id){
        locationRepository.deleteById(id);
    }

    public  Location findById(int id){
        return   locationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Location Not Found by this Id"));
    }

}
