package com.platformdevlab.tasks.repository;

import com.platformdevlab.tasks.model.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends  ReactiveMongoRepository<Task, String> {

}
