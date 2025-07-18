package com.platformdevlab.tasks.controller.converter;

import com.platformdevlab.tasks.controller.dto.TaskInsetDTO;
import com.platformdevlab.tasks.controller.dto.TaskUpdateDTO;
import com.platformdevlab.tasks.model.Task;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskUpadteDTOConverter {

    public Task convert(TaskUpdateDTO dto) {
        return Optional.ofNullable(dto)
                .map(source -> Task.builder()
                        .withId(source.getId())
                        .withTitle(source.getTitle())
                        .withDescription(source.getDescription())
                        .withPriority(source.getPriority())
                        .build())
                .orElse(null);


    }

}
