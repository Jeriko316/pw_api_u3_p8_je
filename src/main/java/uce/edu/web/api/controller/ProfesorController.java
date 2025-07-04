package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @GET
    public Response buscarTodos(@QueryParam("genero") String genero) {
        List<Profesor> profesores = this.profesorService.buscarTodos(genero);
        return Response.ok(profesores).build();
    }

    @GET
    @Path("/{id}")
     @Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo prof = this.profesorService.buscarPorId(id, uriInfo);
        return Response.status(227).entity(prof).build();
    }

    @POST
    public Response guardar(Profesor profesor) {
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarPorId(@PathParam("id") Integer id, Profesor profesor) {
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        return Response.noContent().build();
    }

   /*  @PATCH
    @Path("/{id}")
    public Response actualizarParcialPorId(@PathParam("id") Integer id, Profesor profesor) {
        profesor.setId(id);
        Profesor p = this.profesorService.buscarPorId(id);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (profesor.getApellido() != null) p.setApellido(profesor.getApellido());
        if (profesor.getNombre() != null) p.setNombre(profesor.getNombre());
        if (profesor.getCedula() != null) p.setCedula(profesor.getCedula());
        this.profesorService.actualizarParcialPorId(p);
        return Response.noContent().build();
    }*/

    @DELETE
    @Path("/{id}")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.noContent().build();
    }

     @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id){
        Hijo h1 = new Hijo();
        h1.setNombre("carlitos");

        Hijo h2 = new Hijo();
        h2.setNombre("guano y la luisa");

        List<Hijo> hijos= new ArrayList<>();
        hijos.add(h1);
        hijos.add(h2);
        return hijos;
    }
}
