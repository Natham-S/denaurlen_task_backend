package com.natham.denaurlen_task_backend.service;

import com.natham.denaurlen_task_backend.model.UserCredential;
import com.natham.denaurlen_task_backend.repo.UserCredentialRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserCredentialRepository userRepo;

    public AuthService(UserCredentialRepository userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<String> verifyCredentials(UserCredential credentials) {
        Optional<UserCredential> userOptional = userRepo.findByUsername(credentials.getUsername());

        if (userOptional.isPresent()) {
            if (userOptional.get().getName().equalsIgnoreCase(credentials.getName())) {
                System.out.println("User '" + credentials.getUsername() + "' logged in successfully.");
                return ResponseEntity.ok("Verified");
            } else {
                System.out.println("Login attempt failed for user '" + credentials.getUsername() + "'. Name does not match.");
                throw new RuntimeException("Username exists, but name does not match.");
            }
        } else {
            System.out.println("Login attempt failed. User '" + credentials.getUsername() + "' not found.");
            throw new RuntimeException("User not found.");
        }
    }
}
