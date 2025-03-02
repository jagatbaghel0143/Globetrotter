package com.headout.globetrotter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headout.globetrotter.entity.Clue;
import com.headout.globetrotter.repository.ClueRepository;

@Service
public class ClueService {
    private final ClueRepository clueRepository;

    @Autowired
    public ClueService(ClueRepository clueRepository) {
        this.clueRepository = clueRepository;
    }

    List<Clue> getCluesByDestinationId(Long destinationId) {
        return clueRepository.findByDestinationId(destinationId);
    }
}
