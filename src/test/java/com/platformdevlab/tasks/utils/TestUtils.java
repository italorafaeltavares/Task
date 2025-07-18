package com.platformdevlab.tasks.utils;

import com.platformdevlab.tasks.controller.dto.TaskDTO;
import com.platformdevlab.tasks.model.Task;
import com.platformdevlab.tasks.model.TaskState;

public class TestUtils {

    public static Task buildValidTask() {
        return Task.builder()
                .withId("123")
                .withTitle("title")
                .withPriority(1)
                .withDescription("Description")
                .withState(TaskState.INSERT)
                .build();
    }

    public static TaskDTO buildValidTaskDTO() {
        TaskDTO dto = new TaskDTO();
            dto.setId("123");
            dto.setTitle("title");
            dto.setDescription("Description");
            dto.setPriority(1);
            dto.setState(TaskState.INSERT);
            return dto;
    }
}
