// src/main/java/org/acme/gateway/client/TaskService.java
package org.acme.client;

import org.acme.dto.TaskDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@RegisterRestClient(configKey="task-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TaskService {

    @GET
    List<TaskDTO> getAllTasks();

    @GET
    @Path("/{id}")
    TaskDTO getTask(@PathParam("id") Long id);

    @GET
    @Path("/category/{categoryId}")
    List<TaskDTO> getTasksByCategory(@PathParam("categoryId") Long categoryId);

    @POST
    Response createTask(TaskDTO task);

    @PUT
    @Path("/{id}")
    Response updateTask(@PathParam("id") Long id, TaskDTO task);

    @DELETE
    @Path("/{id}")
    Response deleteTask(@PathParam("id") Long id);
}
