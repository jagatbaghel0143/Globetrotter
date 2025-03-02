package com.headout.globetrotter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.headout.globetrotter.entity.User;
import com.headout.globetrotter.service.UserService;

import lombok.Data;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint for registering a user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User userRequest) {
        User user = userService.registerNewUser(userRequest.getUsername());
        return ResponseEntity.ok(user);
    }

    // Endpoint to update score after a guess
    @PostMapping("/{username}/score")
    public ResponseEntity<?> updateUserScore(@PathVariable String username, @RequestParam boolean correct) {
        User user = userService.updateScore(username, correct);
        return ResponseEntity.ok(user);
    }

    // Endpoint to generate challenge invitation link and dynamic image URL
    @GetMapping("/challenge")
    public ResponseEntity<?> challengeFriend(@RequestParam String username) {
        // You can generate a unique URL that contains the user’s score.
        String inviteLink = "https://globetrotter.app/challenge?user=" + username;
        // Optionally, call a third-party API/service to generate a dynamic image URL.
        String dynamicImageUrl = "https://dummyimage.com/600x400/000/fff&text=" + username + "+Score";
        ChallengeResponse response = new ChallengeResponse(inviteLink, dynamicImageUrl);
        return ResponseEntity.ok(response);
    }
}

@Data
class ChallengeResponse {
    private String inviteLink;
    private String imageUrl;

    public ChallengeResponse(String inviteLink, String imageUrl) {
        this.inviteLink = inviteLink;
        this.imageUrl = imageUrl;
    }
}