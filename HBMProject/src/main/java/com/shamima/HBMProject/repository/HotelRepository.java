package com.shamima.HBMProject.repository;

import com.shamima.HBMProject.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findHotelsByLocationName(String locationName);
}
