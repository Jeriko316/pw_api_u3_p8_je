package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo prof = ProfesorMapper.toTo(this.profesorService.buscarPorId(id));
        // Si tienes buildURI en ProfesorTo, puedes usarlo aquí
        // prof.buildURI(uriInfo);
        return Response.status(227).entity(prof).build();
    }

    @GET
    @Path("")
    @Operation(
        summary = "consultar profesor",
        description = "este end point permite consultar todos los profesores"
    )
    public Response consultarTodos(@QueryParam("genero") String genero) {
        List<ProfesorTo> lista = this.profesorService.buscarTodos(genero)
            .stream()
            .map(ProfesorMapper::toTo)
            .toList();
        return Response.status(Response.Status.OK).entity(lista).build();
    }

    @POST
    @Path("")
    @Operation(
        summary = "guardar profesor",
        description = "esta capacidad permite guardar un profesor"
    )
    public void guardar(@RequestBody ProfesorTo profesorTo) {
        this.profesorService.guardar(ProfesorMapper.toEntity(profesorTo));
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, @RequestBody ProfesorTo profesorTo) {
        var profesor = ProfesorMapper.toEntity(profesorTo);
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody ProfesorTo profesorTo) {
        profesorTo.setId(id);
        Profesor p = this.profesorService.buscarPorId(id);
        if (profesorTo.getNombre() != null) {
            p.setNombre(profesorTo.getNombre());
        }
        if (profesorTo.getApellido() != null) {
            p.setApellido(profesorTo.getApellido());
        }
        if (profesorTo.getCedula() != null) {
            p.setCedula(profesorTo.getCedula());
        }
        if (profesorTo.getGenero() != null) {
            p.setGenero(profesorTo.getGenero());
        }
        this.profesorService.actualizarParcialPorId(p);
    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
    }
}
