package uce.edu.web.api.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

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
    public Response buscarPorId(@PathParam("id") Integer id) {
        Profesor profesor = this.profesorService.buscarPorId(id);
        if (profesor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(profesor).build();
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

    @PATCH
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
    }

    @DELETE
    @Path("/{id}")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.noContent().build();
    }
}
