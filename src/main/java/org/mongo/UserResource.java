package org.mongo;

import io.quarkus.runtime.Application;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


    @POST
    public void addUser(@RequestBody User user){
        User.persist(user);
//        user.persist();
    }

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") String id){
        return User.findById(new ObjectId(id));
    }


    @GET
    @Path("/name/{name}")
    public Response getUserByName(@PathParam("name") String name){
       User user= User.find("name",name).firstResult();
        return Response.ok(user).build();
    }


}
