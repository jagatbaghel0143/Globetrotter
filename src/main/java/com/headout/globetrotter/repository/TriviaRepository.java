package com.headout.globetrotter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.headout.globetrotter.entity.Trivia;

public interface TriviaRepository extends JpaRepository<Trivia, Long> {
    
}
