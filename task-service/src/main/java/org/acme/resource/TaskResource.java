// task-service/src/main/java/org/acme/resource/TaskResource.java
package org.acme.resource;

import io.quarkus.panache.common.Sort;
import org.acme.entity.Task;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @GET
    public List<Task> getAllTasks() {
        return Task.listAll(Sort.by("id"));
    }

    @GET
    @Path("/{id}")
    public Task getTask(@PathParam("id") Long id) {
        return Task.findById(id);
    }

    @GET
    @Path("/category/{categoryId}")
    public List<Task> getTasksByCategory(@PathParam("categoryId") Long categoryId) {
        return Task.list("categoryId", categoryId);
    }

    @POST
    @Transactional
    public Response createTask(Task task) {
        task.persist();
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
        Task task = Task.findById(id);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        task.title = updatedTask.title;
        task.description = updatedTask.description;
        task.completed = updatedTask.completed;
        task.categoryId = updatedTask.categoryId;
        task.persist();
        return Response.ok(task).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTask(@PathParam("id") Long id) {
        Task task = Task.findById(id);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        task.delete();
        return Response.noContent().build();
    }
}