package org.microservice.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.*;

@Path("/books")
public class BookResource {
    private static Map<Integer, org.microservice.model.v1.Book> library = new HashMap<Integer, org.microservice.model.v1.Book>();

    @GET
    @Path("/enhancedmediatype")
    @Produces("application/org.microservice.api.v1.book+json;qs=0.5")
    public Response getVersion1() {
        org.microservice.model.v1.Book book;

        book = new org.microservice.model.v1.Book(99, "title-version1", "author-version1");
        return Response.ok(book).build();
    }

    @GET
    @Path("/enhancedmediatype")
    //@Produces({"application/json", "application/vnd.library.book.v2+json;qs=0.9"})
    @Produces("application/org.microservice.api.v2.book+json;qs=0.9")
    public Response getVersion2() {
        org.microservice.model.v2.Book book;

        book = new org.microservice.model.v2.Book(99, "title-version2", "author-version2");
        return Response.ok(book).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBooks() {
        List<org.microservice.model.v1.Book> books;
        GenericEntity<List<org.microservice.model.v1.Book>> entity;

        books = new ArrayList<org.microservice.model.v1.Book>(library.values());
        entity = new GenericEntity<List<org.microservice.model.v1.Book>>(books) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{key}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBook(@PathParam("key") int key) {

        if (library.containsKey(key)) {
            return Response.ok(library.get(key)).build();
        } else {
            return Response.status(404).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addBook(org.microservice.model.v1.Book book, @Context UriInfo uriInfo) {
        UriBuilder builder;
        URI createdURI;

        if (book.getId() != 0) {
            return Response.status(400).build();
        }
        int createdEmployeeId = 1;
        if (!library.isEmpty()) {
            createdEmployeeId = Collections.max(library.keySet()) + 1;
        }
        book.setId(createdEmployeeId);
        library.put(createdEmployeeId, book);
        builder = uriInfo.getAbsolutePathBuilder();
        createdURI = builder.path(Integer.toString(createdEmployeeId)).build();
        return Response.created(createdURI).build();
    }

    @POST
    @Path("/bulk")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addBook(ArrayList<org.microservice.model.v1.Book> books, @Context UriInfo uriInfo) {
        UriBuilder builder;
        URI createdURI;
        createdURI = null;
        for (org.microservice.model.v1.Book book : books) {
            if (book.getId() != 0) {
                return Response.status(400).build();
            }
            int createdEmployeeId = 1;
            if (!library.isEmpty()) {
                createdEmployeeId = Collections.max(library.keySet()) + 1;
            }
            book.setId(createdEmployeeId);
            library.put(createdEmployeeId, book);
            builder = uriInfo.getAbsolutePathBuilder();
            createdURI = builder.path(Integer.toString(createdEmployeeId)).build();
        }
        return Response.created(createdURI).build();
    }

    @PUT
    @Path("/{key}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateBook(@PathParam("key") int key, org.microservice.model.v1.Book book) {
        int status = 0;

        if (library.containsKey(key)) {
            library.put(key, book);
            status = 204;
        } else {
            status = 404;
        }
        return Response.status(status).build();
    }

    @DELETE
    @Path("/{key}")
    public Response deleteBook(@PathParam("key") int key) {

        library.remove(key);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteBooks() {

        library.clear();
        return Response.noContent().build();
    }
}