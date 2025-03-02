package com.headout.globetrotter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.headout.globetrotter.dto.ResponseToGuess;
import com.headout.globetrotter.entity.Clue;
import com.headout.globetrotter.entity.Destination;
import com.headout.globetrotter.entity.FunFact;
import com.headout.globetrotter.entity.Session;
import com.headout.globetrotter.entity.Trivia;
import com.headout.globetrotter.entity.User;
import com.headout.globetrotter.repository.ClueRepository;
import com.headout.globetrotter.repository.DestinationRepository;
import com.headout.globetrotter.repository.SessionRepository;
import com.headout.globetrotter.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.time.Instant;

@Service
public class GameService {
    private final ClueRepository clueRepository;
    private final DestinationRepository destinationRepository;
    private final SessionRepository sessionRepository;
    private final ResponseToGuess responseToGuess;
    private final UserRepository userRepository;

    @Autowired
    public GameService(ClueRepository clueRepository, DestinationRepository destinationRepository, SessionRepository sessionRepository, ResponseToGuess responseToGuess, UserRepository userRepository) {
        this.clueRepository = clueRepository;
        this.destinationRepository = destinationRepository;
        this.sessionRepository = sessionRepository;
        this.responseToGuess = responseToGuess;
        this.userRepository = userRepository;
    }

    @Value("${maximumDestinationsAvailable}")
    private int maximumDestinations;

    // In-memory session storage (replace with Redis/DB for production)
    private final Map<String, Session> sessions = new HashMap<>();

    @Transactional(readOnly = true)
    public Session startGame(String userName) {
        String sessionId = UUID.randomUUID().toString();
        Long randomNumber = ThreadLocalRandom.current().nextLong(1, maximumDestinations + 1L);
        List<Clue> clues = clueRepository.findByDestinationId(randomNumber);
        Destination destination = destinationRepository.findById(randomNumber).orElse(null);
        List<FunFact> funFacts = destination.getFunFacts();
        List<Trivia> trivia = destination.getTrivia();

        List<String> clueList = new ArrayList<>();
        for (Clue clue : clues) {
            clueList.addAll(clue.getClues());
        }

        List<String> funFactList = new ArrayList<>();
        for (FunFact funFact : funFacts) {
            funFactList.addAll(funFact.getFunFactTexts());
        }

        List<String> triviaList = new ArrayList<>();
        for (Trivia triviaItem : trivia) {
            triviaList.addAll(triviaItem.getTriviaTexts());
        }

        Session session = new Session();
        session.setSessionId(sessionId);
        session.setUserName(userName);
        // session.setDestination(destination);
        session.setClues(clueList);
        session.setFunFacts(funFactList);
        session.setTrivia(triviaList);
        session.setStartTime(Instant.now().toEpochMilli());
        sessionRepository.save(session);
        return session;
    }

    public ResponseToGuess verifyGuess(String sessionId, String guess) {
        Session session = sessionRepository.findById(sessionId).orElse(null);
        if (session == null) {
            throw new IllegalArgumentException("Session not found");
        }
        // boolean isCorrect = guess.equalsIgnoreCase(session.getDestination().getCityName());
        // String message = isCorrect ? "Well done! It’s " + session.getDestination().getCityName() + "!" : "Nope!";
        // responseToGuess.setSuccess(isCorrect);
        // responseToGuess.setMessage(message);
        responseToGuess.setFunFacts(session.getFunFacts());
        responseToGuess.setTrivia(session.getTrivia());
        return responseToGuess;
    }

    // Optional: Clean up expired sessions (e.g., run via @Scheduled)
    public void cleanExpiredSessions() {
        long now = Instant.now().toEpochMilli();
        sessions.entrySet().removeIf(entry ->
            (now - entry.getValue().getStartTime()) > 30 * 60 * 1000); // 30 min expiry
    }
}