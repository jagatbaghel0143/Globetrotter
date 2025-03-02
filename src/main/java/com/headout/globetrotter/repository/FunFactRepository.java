package com.headout.globetrotter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.headout.globetrotter.entity.FunFact;

public interface FunFactRepository extends JpaRepository<FunFact, Long> {
}
