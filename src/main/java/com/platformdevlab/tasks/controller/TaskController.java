package com.platformdevlab.tasks.controller;

import com.platformdevlab.tasks.controller.converter.TaskDTOConverter;
import com.platformdevlab.tasks.controller.converter.TaskInsertDTOConverter;
import com.platformdevlab.tasks.controller.converter.TaskUpadteDTOConverter;
import com.platformdevlab.tasks.controller.dto.TaskDTO;
import com.platformdevlab.tasks.controller.dto.TaskInsetDTO;
import com.platformdevlab.tasks.controller.dto.TaskUpdateDTO;
import com.platformdevlab.tasks.model.Task;
import com.platformdevlab.tasks.model.TaskState;
import com.platformdevlab.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.awt.image.LookupOp;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService service;

    private final TaskDTOConverter converter;

    private final TaskInsertDTOConverter insertDTOConverter;

    private  final TaskUpadteDTOConverter upadteDTOConverter;

    public TaskController(TaskService service,
                          TaskDTOConverter converter,
                          TaskInsertDTOConverter insertDTOConverter, TaskUpadteDTOConverter upadteDTOConverter) {
        this.service = service;
        this.converter = converter;
        this.insertDTOConverter = insertDTOConverter;
        this.upadteDTOConverter = upadteDTOConverter;
    }

    @GetMapping
    public Mono<Page<TaskDTO>> getTasks(@RequestParam(required = false) String id,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false, defaultValue = "0") int priority,
                                  @RequestParam(required = false) TaskState taskState,
                                  @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return service.findPaginated(converter.convert(id, title, description, priority, taskState), pageNumber, pageSize)
                .map(it -> it.map(converter::convert));
    }

    @PostMapping
    public Mono<TaskDTO> createTask(@RequestBody @Valid TaskInsetDTO taskInsetDTO){
        return service.insert(insertDTOConverter.convert(taskInsetDTO))
                .doOnNext(task -> LOGGER.info("Saved task with id {}", task.getId()))
                .map(converter::convert);
    }

    @PutMapping
    public  Mono<TaskDTO> updateTask(@RequestBody @Valid TaskUpdateDTO taskUpdateDTO) {
        return service.update(upadteDTOConverter.convert(taskUpdateDTO))
                .doOnNext(it -> LOGGER.info("Update task with id {}", it.getId()))
                .map(converter::convert);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return Mono.just(id)
                .doOnNext(it -> LOGGER.info("Deleting task with id {}", id))
                .flatMap(service::deleteById);
    }

    @PostMapping("/start")
    public Mono<TaskDTO> start(@RequestParam String id, @RequestParam String zipcode) {
        return service.start(id, zipcode)
                .map(converter::convert);
    }
}
