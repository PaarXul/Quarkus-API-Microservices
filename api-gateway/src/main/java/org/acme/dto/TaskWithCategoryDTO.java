// api-gateway/src/main/java/org/acme/gateway/dto/TaskWithCategoryDTO.java
package org.acme.dto;

public class TaskWithCategoryDTO {
    public Long id;
    public String title;
    public String description;
    public boolean completed;
    public Long categoryId;
    public String categoryName;

    // Constructor vacío necesario para la serialización
    public TaskWithCategoryDTO() {}

    // Constructor con todos los campos
    public TaskWithCategoryDTO(TaskDTO task, String categoryName) {
        this.id = task.id;
        this.title = task.title;
        this.description = task.description;
        this.completed = task.completed;
        this.categoryId = task.categoryId;
        this.categoryName = categoryName;
    }
}