package com.headout.globetrotter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headout.globetrotter.entity.User;
import com.headout.globetrotter.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(String username) {
        User existing = userRepository.findByUsername(username);
        if(existing != null) {
            return existing;
        }
        User user = new User();
        user.setUsername(username);
        user.setCorrectAnswers(0);
        user.setWrongAnswers(0);
        return userRepository.save(user);
    }

    public User updateScore(String username, boolean isCorrect) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if(isCorrect) {
                user.setCorrectAnswers(user.getCorrectAnswers() + 1);
            } else {
                user.setWrongAnswers(user.getWrongAnswers() + 1);
            }
            userRepository.save(user);
        }
        return user;
    }
}
