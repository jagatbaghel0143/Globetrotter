package com.headout.globetrotter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.headout.globetrotter.entity.Clue;

public interface ClueRepository extends JpaRepository<Clue, Long> {
    @Query(value = "SELECT * FROM clue WHERE destination_id = ?1", nativeQuery = true)
    List<Clue> findByDestinationId(Long destinationId);
}
