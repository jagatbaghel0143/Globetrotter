package com.headout.globetrotter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.headout.globetrotter.entity.Session;

public interface SessionRepository extends JpaRepository<Session, String> {
}
