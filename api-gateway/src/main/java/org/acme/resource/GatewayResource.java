// src/main/java/org/acme/gateway/GatewayResource.java
package org.acme.resource;

import org.acme.client.TaskService;
import org.acme.client.CategoryService;
import org.acme.dto.TaskDTO;
import org.acme.dto.CategoryDTO;
import org.acme.dto.TaskWithCategoryDTO;
import org.acme.exception.ErrorResponse;
import org.acme.exception.ServiceException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GatewayResource {

    @Inject
    @RestClient
    TaskService taskService;

    @Inject
    @RestClient
    CategoryService categoryService;

    // Task endpoints
    @GET
    @Path("/tasks")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GET
    @Path("/tasks/{id}")
    public TaskDTO getTask(@PathParam("id") Long id) {
        return taskService.getTask(id);
    }

    @GET
    @Path("/tasks/category/{categoryId}")
    public List<TaskDTO> getTasksByCategory(@PathParam("categoryId") Long categoryId) {
        return taskService.getTasksByCategory(categoryId);
    }

    @POST
    @Path("/tasks")
    public Response createTask(TaskDTO task) {
        return taskService.createTask(task);
    }

    @PUT
    @Path("/tasks/{id}")
    public Response updateTask(@PathParam("id") Long id, TaskDTO task) {
        return taskService.updateTask(id, task);
    }

    @DELETE
    @Path("/tasks/{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        return taskService.deleteTask(id);
    }

    // Category endpoints
    @GET
    @Path("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GET
    @Path("/categories/{id}")
    public CategoryDTO getCategory(@PathParam("id") Long id) {
        return categoryService.getCategory(id);
    }

    @POST
    @Path("/categories")
    public Response createCategory(CategoryDTO category) {
        return categoryService.createCategory(category);
    }

    @PUT
    @Path("/categories/{id}")
    public Response updateCategory(@PathParam("id") Long id, CategoryDTO category) {
        return categoryService.updateCategory(id, category);
    }

    @DELETE
    @Path("/categories/{id}")
    public Response deleteCategory(@PathParam("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @GET
    @Path("/tasks/with-category")
    public Response getTasksWithCategories() {
        try {
            List<CategoryDTO> categories = categoryService.getAllCategories();
            Map<Long, String> categoryMap = categories.stream()
                    .collect(Collectors.toMap(
                            category -> category.id,
                            category -> category.name,
                            (existing, replacement) -> existing
                    ));

            List<TaskDTO> tasks = taskService.getAllTasks();
            List<TaskWithCategoryDTO> tasksWithCategories = tasks.stream()
                    .map(task -> new TaskWithCategoryDTO(
                            task,
                            categoryMap.getOrDefault(task.categoryId, "Sin categoría")
                    ))
                    .collect(Collectors.toList());

            return Response.ok(tasksWithCategories).build();
        } catch (Exception e) {
            throw new ServiceException("Error al obtener tareas con categorías", e);
        }
    }

    @GET
    @Path("/tasks/{id}/with-category")
    public Response getTaskWithCategory(@PathParam("id") Long id) {
        try {
            TaskDTO task = taskService.getTask(id);
            if (task == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Tarea no encontrada"))
                        .build();
            }

            String categoryName = "Sin categoría";
            if (task.categoryId != null) {
                CategoryDTO category = categoryService.getCategory(task.categoryId);
                if (category != null) {
                    categoryName = category.name;
                }
            }

            TaskWithCategoryDTO taskWithCategory = new TaskWithCategoryDTO(task, categoryName);
            return Response.ok(taskWithCategory).build();
        } catch (Exception e) {
            throw new ServiceException("Error al obtener tarea con categoría", e);
        }
    }

}