// src/main/java/org/acme/gateway/client/CategoryService.java
package org.acme.client;

import org.acme.dto.CategoryDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
@RegisterRestClient(configKey="category-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CategoryService {

    @GET
    List<CategoryDTO> getAllCategories();

    @GET
    @Path("/{id}")
    CategoryDTO getCategory(@PathParam("id") Long id);

    @POST
    Response createCategory(CategoryDTO category);

    @PUT
    @Path("/{id}")
    Response updateCategory(@PathParam("id") Long id, CategoryDTO category);

    @DELETE
    @Path("/{id}")
    Response deleteCategory(@PathParam("id") Long id);
}