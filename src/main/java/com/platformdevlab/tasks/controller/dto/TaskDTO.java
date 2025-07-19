package com.platformdevlab.tasks.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.platformdevlab.tasks.model.Address;
import com.platformdevlab.tasks.model.TaskState;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

    @NotBlank(message = "{invalid.id}")
    private String id;

    @NotBlank(message = "{blank.title}")
    @Size(min =3, max=20, message = "{size.title}")
    private String title;

    @NotBlank(message = "{blank.description}")
    @Size(min =10, max=50, message = "{size.description}")
    private String description;

    @Min(value = 1, message = "{min.priority}")
    private int priority;

    @NotBlank(message = "{invalid.state}")
    private TaskState state;

    private Address address;
    private LocalDate created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
