package com.platformdevlab.tasks.messaging;

import com.platformdevlab.tasks.model.Task;
import com.platformdevlab.tasks.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TaskNotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskNotificationConsumer.class);

    private final TaskService taskService;

    public TaskNotificationConsumer(TaskService taskService) {
        this.taskService = taskService;
    }

    @KafkaListener(topics = "${kafka.task.notification-output}", groupId = "${kafka.task.notification-group.id}")
    public void  receiverAndFinishTask(Task task) {
        Mono.just(task)
                .doOnNext(it -> LOGGER.info("Receiving task to finish. ID {}", task.getId()))
                .flatMap(taskService::done)
                .block();
    }
}
