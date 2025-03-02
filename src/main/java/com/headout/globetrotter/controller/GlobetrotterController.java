package com.headout.globetrotter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.headout.globetrotter.dto.GuessRequest;
import com.headout.globetrotter.dto.ResponseToGuess;
import com.headout.globetrotter.entity.Session;
import com.headout.globetrotter.repository.UserRepository;
import com.headout.globetrotter.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GlobetrotterController {
    private final GameService gameService;
    private final UserRepository userRepository;

    @Autowired
    public GlobetrotterController(GameService gameService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.gameService = gameService;
    }

    @PostMapping("{username}/start")
    public ResponseEntity<Session> startGame(@PathVariable String username) {
        if(username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if(userRepository.findByUsername(username) == null) {
            return ResponseEntity.notFound().build();
        }
        Session session = gameService.startGame(username);
        return ResponseEntity.ok(session);
    }

    @PostMapping("{username}/guess")
    public ResponseEntity<ResponseToGuess> verifyGuess(@PathVariable String username, @RequestBody GuessRequest request) {
        if(username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if(userRepository.findByUsername(username) == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            ResponseToGuess response = gameService.verifyGuess(request.getSessionId(), request.getGuess());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(new ResponseToGuess(false, e.getMessage(), null, null));
        }
    }
}
