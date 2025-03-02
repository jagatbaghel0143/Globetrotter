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
@Table(name = "clue")
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "clue_text", nullable = false)
    private List<String> clues;

    public Clue(String clueText) {
        this.clues = new ArrayList<>(Collections.singletonList(clueText));
    }
}
