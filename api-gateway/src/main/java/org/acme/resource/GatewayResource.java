// src/main/java/org/acme/gateway/GatewayResource.java
package org.acme.resource;

import org.acme.client.TaskService;
import org.acme.client.CategoryService;
import org.acme.dto.TaskDTO;
import org.acme.dto.CategoryDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

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


}