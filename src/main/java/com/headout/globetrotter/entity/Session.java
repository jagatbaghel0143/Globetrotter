package com.headout.globetrotter.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "session")
public class Session {
    @Id
    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    // private Destination destination;

    @Column(name = "clues", nullable = false)
    private List<String> clues;

    @Column(name = "fun_facts", nullable = false)
    private List<String> funFacts;

    @Column(name = "trivia", nullable = false)
    private List<String> trivia;

    @Column(name = "start_time", nullable = false)
    private long startTime;
}
