package com.shamima.HBMProject.repository;

import com.shamima.HBMProject.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findHotelsByLocationName(String locationName);
}
