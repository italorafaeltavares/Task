package com.platformdevlab.tasks.controller;

import com.mongodb.internal.connection.StreamFactoryHelper;
import com.platformdevlab.tasks.controller.converter.TaskDTOConverter;
import com.platformdevlab.tasks.controller.converter.TaskInsertDTOConverter;
import com.platformdevlab.tasks.controller.dto.TaskDTO;
import com.platformdevlab.tasks.controller.dto.TaskInsetDTO;
import com.platformdevlab.tasks.model.Task;
import com.platformdevlab.tasks.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskControllerTest {

    @InjectMocks
    private TaskController controller;

    @Mock
    private TaskService service;

    @Mock
    private TaskDTOConverter converter;

    @Mock
    private TaskInsertDTOConverter insertDTOConverter;

    @Test
    public void controller_mustReturnOK_whenSaveSuccessfully() {
        when(converter.convert(any(Task.class))).thenReturn(new TaskDTO());
        when(service.insert(any())).thenReturn(Mono.just(new Task()));

        WebTestClient client = WebTestClient.bindToController(controller).build();

        client.post()
                .uri("/task")
                .bodyValue(new TaskInsetDTO())
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskDTO.class);
    }

    @Test
    public void controller_mustReturnOK_whenGetPaginatedSuccessfully() {
        when(service.findPaginated(any(), anyInt(), anyInt())).thenReturn(Mono.just(Page.empty()));

        WebTestClient client = WebTestClient.bindToController(controller).build();

        client.get()
                .uri("/task")
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskDTO.class);

    }

    @Test
    public void controller_mustReturnNoContent_whenDeleteSuccessfully() {

        String taskId = "any-id";

        when(service.deleteById(any())).thenReturn(Mono.empty());
        WebTestClient client = WebTestClient.bindToController(controller).build();

        client.delete()
                .uri("/task/" + taskId)
                .exchange()
                .expectStatus()
                .isNoContent();
    }

}
