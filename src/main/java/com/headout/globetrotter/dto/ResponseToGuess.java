package com.headout.globetrotter.dto;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseToGuess {
    private boolean success;
    private String message;
    private List<String> funFacts;
    private List<String> trivia;
}
