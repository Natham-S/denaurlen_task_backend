package com.natham.denaurlen_task_backend.controller;


import com.natham.denaurlen_task_backend.model.PostData;
import com.natham.denaurlen_task_backend.model.UserCredential;
import com.natham.denaurlen_task_backend.model.Valuation;
import com.natham.denaurlen_task_backend.repo.PostDataRepository;
import com.natham.denaurlen_task_backend.repo.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AppController {

    @Autowired
    private UserCredentialRepository userRepo;

    @Autowired
    private PostDataRepository postRepo;


    @PostMapping("/credentials")
    public ResponseEntity<String> verifyCredentials(@RequestBody UserCredential credentials) {
        Optional<UserCredential> userOptional = userRepo.findByUsername(credentials.getUsername());

        if (userOptional.isPresent()) {
            if (userOptional.get().getName().equalsIgnoreCase(credentials.getName())) {
                System.out.println("User '" + credentials.getUsername() + "' logged in successfully.");
                return ResponseEntity.ok("Verified");
            } else {
                System.out.println("Login attempt failed for user '" + credentials.getUsername() + "'. Name does not match.");
                throw new RuntimeException("Username exists, but name does not match.");
            }
        }else {
            System.out.println("Login attempt failed. User '" + credentials.getUsername() + "' not found.");
            throw new RuntimeException("User not found.");
        }


    }


    @PostMapping("/initialize")
    public PostData initializePost() {
        PostData post = new PostData();
        post.setLeadUser("terry_dias");
        post.setNetCoins(1100);
        post.setGrossCoins(2100);
        post.setCreatedAt(LocalDateTime.now());
        System.out.println("Initialized post Successfully");
        return postRepo.save(post);
    }


    @GetMapping("/getpostdata")
    public PostData getPostData() {
        System.out.println("Requested post data");
        return postRepo.findAll().get(0);
    }


    @PostMapping("/lead")
    public PostData performValuation(@RequestParam String username) {

        UserCredential user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));

        PostData post = postRepo.findAll().get(0);

        if (post.getCreatedAt().plusHours(144).isBefore(LocalDateTime.now())) {
            System.out.println("Valuation period expired.");
            throw new RuntimeException("Valuation period expired.");
        }

        int newNetCoins = post.getNetCoins() + 100;

        int newGrossCoins = post.getGrossCoins() + newNetCoins;

        post.setLeadUser(username);
        post.setNetCoins(newNetCoins);
        post.setGrossCoins(newGrossCoins);

        Valuation valuation = new Valuation(user.getName(), username, newNetCoins, newGrossCoins);
        post.getValuationHistory().add(valuation);

        System.out.println("Lead Successfully updated");
        return postRepo.save(post);
    }
}
