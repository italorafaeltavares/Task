package com.platformdevlab.tasks.controller.converter;

import com.platformdevlab.tasks.controller.dto.TaskInsetDTO;
import com.platformdevlab.tasks.model.Task;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskInsertDTOConverter {

    public Task convert(TaskInsetDTO dto) {
        return Optional.ofNullable(dto)
                .map(source -> Task.builder()
                        .withTitle(source.getTitle())
                        .withDescription(source.getDescription())
                        .withPriority(source.getPriority())
                        .build())
                .orElse(null);


    }

}
