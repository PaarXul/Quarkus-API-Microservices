// category-service/src/main/java/org/acme/resource/CategoryResource.java
package org.acme.resource;

import io.quarkus.panache.common.Sort;
import org.acme.entity.Category;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @GET
    public List<Category> getAllCategories() {
        return Category.listAll(Sort.by("id"));
    }

    @GET
    @Path("/{id}")
    public Category getCategory(@PathParam("id") Long id) {
        return Category.findById(id);
    }

    @POST
    @Transactional
    public Response createCategory(Category category) {
        category.persist();
        return Response.status(Response.Status.CREATED).entity(category).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCategory(@PathParam("id") Long id, Category updatedCategory) {
        Category category = Category.findById(id);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        category.name = updatedCategory.name;
        category.persist();
        return Response.ok(category).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCategory(@PathParam("id") Long id) {
        Category category = Category.findById(id);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        category.delete();
        return Response.noContent().build();
    }
}