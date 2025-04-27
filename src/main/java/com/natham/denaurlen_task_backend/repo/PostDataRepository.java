package com.natham.denaurlen_task_backend.repo;

import com.natham.denaurlen_task_backend.model.PostData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostDataRepository extends MongoRepository<PostData, String> {
}
