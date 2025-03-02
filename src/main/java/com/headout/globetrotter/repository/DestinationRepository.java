package com.headout.globetrotter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.headout.globetrotter.entity.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    
    @Query(value = "SELECT * FROM destinations ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Destination findRandomDestination();
}
