package com.natham.denaurlen_task_backend.repo;

import com.natham.denaurlen_task_backend.model.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserCredentialRepository extends MongoRepository<UserCredential, String> {
    Optional<UserCredential> findByUsername(String username);
}