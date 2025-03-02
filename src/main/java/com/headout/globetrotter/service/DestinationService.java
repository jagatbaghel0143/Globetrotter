package com.headout.globetrotter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headout.globetrotter.entity.Destination;
import com.headout.globetrotter.repository.DestinationRepository;

@Service
public class DestinationService {
    
    private DestinationRepository destinationRepository;
    
    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> createDestination(List<Destination> destinations) {
        return destinationRepository.saveAll(destinations);
    }

    public Destination getRandomDestinationWithClues() {
        Destination destination = destinationRepository.findRandomDestination();
        if (destination != null && destination.getClues() != null) {
            // List<String> clues = destination.getClues();
            // Collections.shuffle(clues);
            // int numberOfClues = Math.min(2, clues.size());
            // destination.setClues(clues.subList(0, numberOfClues));
            // List<Clue> clues = destination.getClues();
            // Collections.shuffle(clues);
            // int numberOfClues = Math.min(2, clues.size());
            // destination.setClues(clues.subList(0, numberOfClues));
        }
        return destination;
    }

}
