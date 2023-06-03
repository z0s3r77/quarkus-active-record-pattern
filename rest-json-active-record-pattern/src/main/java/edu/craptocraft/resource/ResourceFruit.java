package edu.craptocraft.resource;


import edu.craptocraft.model.Fruit;
import edu.craptocraft.service.ServiceFruit;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;
import java.util.Set;


@Path("/fruits")
public class ResourceFruit {

    @Inject
    ServiceFruit service;

    public ResourceFruit(){}

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "Colmados Farmer Rick";
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Fruit> list(){
        return service.list();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Set<Fruit> add(@Valid Fruit fruit){
        service.add(fruit);
        return this.list();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Set<Fruit> delete(@Valid Fruit fruit){
        service.remove(fruit.getName());
        return this.list();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("name") String name){
        Optional<Fruit> fruit = service.getFruit(name);
        return fruit.isPresent() ?
                Response.status(Response.Status.OK).entity(fruit.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }



}
