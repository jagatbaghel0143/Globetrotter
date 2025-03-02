package com.headout.globetrotter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.headout.globetrotter.entity.Clue;
import com.headout.globetrotter.entity.Destination;
import com.headout.globetrotter.repository.ClueRepository;
import com.headout.globetrotter.repository.DestinationRepository;
import com.headout.globetrotter.service.DestinationService;

import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;
    private final DestinationRepository destinationRepository;
    private final ClueRepository clueRepository;

    @Autowired
    public DestinationController(DestinationService destinationService, ClueRepository clueRepository, DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
        this.clueRepository = clueRepository;
        this.destinationService = destinationService;
    }

    @GetMapping("/getclues")
    public ResponseEntity<?> getClues() {
        int min = 1;
        int max = 3;
        Long randomNumber = ThreadLocalRandom.current().nextLong(min, max + 1);
        List<Clue> clues = clueRepository.findByDestinationId(randomNumber);
        List<String> clueList = new ArrayList<>();
        for(Clue clue: clues) {
            clueList.addAll(clue.getClues());
        }
        // clueResponseDTO.setId(randomNumber);
        // clueResponseDTO.setClues(clueList);
        // return ResponseEntity.ok(clueResponseDTO);
        return ResponseEntity.ok(clueList);
    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomDestination() {
        Destination destination = destinationService.getRandomDestinationWithClues();
        if (destination == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(destination);
    }

    @PostMapping("/guess")
    public ResponseEntity<?> guessDestination(@RequestBody GuessRequest request) {
        Destination destination = destinationRepository.findById(1L).orElse(null);
        // Destination destination = destinationRepository.findById(request.getDestinationId()).orElse(null);
        boolean isCorrect = request.getGuess().equalsIgnoreCase(destination.getCityName());
        Feedback feedback = new Feedback();
        feedback.setCorrect(isCorrect);
        feedback.setFunFacts(null);
        feedback.setTrivia(null);
        return ResponseEntity.ok(feedback);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDestination(@RequestBody List<Destination> destinations) {
        List<Destination> savedDestination = destinationService.createDestination(destinations);
        return ResponseEntity.ok(savedDestination);
    }
}

@Data
class GuessRequest {
    private Long destinationId;
    private String guess;
}

@Data
@NoArgsConstructor
class Feedback {
    private boolean correct;
    private List<String> funFacts;
    private List<String> trivia;
    public Feedback(boolean correct, List<String> funFacts, List<String> trivia) {
        this.correct = correct;
        this.funFacts = funFacts;
        this.trivia = funFacts;
    }
}
