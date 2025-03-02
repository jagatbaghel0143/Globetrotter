package com.headout.globetrotter.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Transactional
@AllArgsConstructor
@Table(name = "trivia")
public class Trivia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trivia_text", nullable = false)
    private List<String> triviaTexts;

    public Trivia(String triviaText) {
        this.triviaTexts = new ArrayList<>(Collections.singletonList(triviaText));
    }
}
