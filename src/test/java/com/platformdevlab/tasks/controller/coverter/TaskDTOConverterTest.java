package com.platformdevlab.tasks.controller.coverter;

import com.platformdevlab.tasks.controller.converter.TaskDTOConverter;
import com.platformdevlab.tasks.controller.dto.TaskDTO;
import com.platformdevlab.tasks.model.Task;
import com.platformdevlab.tasks.model.TaskState;
import com.platformdevlab.tasks.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.TaskUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskDTOConverterTest {
    @InjectMocks
    private TaskDTOConverter converter;

    @Test
    void converter_mustReturnTaskDTO_whenInputTask() {
        Task task = TestUtils.buildValidTask();

        TaskDTO dto = converter.convert(task);

        assertEquals(dto.getId(), task.getId());
        assertEquals(dto.getTitle(), task.getTitle());
        assertEquals(dto.getDescription(), task.getDescription());
        assertEquals(dto.getPriority(), task.getPriority());
        assertEquals(dto.getState(), task.getState());
    }

    @Test
    void converter_mustReturnTaskDTO_whenInputTaskDTO() {
        TaskDTO dto = TestUtils.buildValidTaskDTO();

        Task task = converter.convert(dto);

        assertEquals(task.getId(), dto.getId());
        assertEquals(task.getTitle(), dto.getTitle());
        assertEquals(task.getDescription(), dto.getDescription());
        assertEquals(task.getPriority(), dto.getPriority());
        assertEquals(task.getState(), dto.getState());
    }

    @Test
    void converter_mustReturnTask_whenInputParameters(){
        String id = "123";
        String title = "title";
        String description = "Description";
        int priority = 1;
        TaskState taskState = TaskState.INSERT;

        Task task = converter.convert(id, title, description, priority, TaskState.INSERT);

        assertEquals(id, task.getId());
        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(priority, task.getPriority());
        assertEquals(taskState, task.getState());

    }
}
