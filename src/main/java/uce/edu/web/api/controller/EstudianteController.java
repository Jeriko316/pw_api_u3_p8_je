package uce.edu.web.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteController extends BaseControlador {

    @Inject
    private IEstudianteService estudianteService;

    @Inject
    private IHijoService hijoService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
        return Response.status(227).entity(estu).build();
    }

    @GET
    @Path("")
    @Operation(
        summary = "consultar estudiante",
        description = "este end point permite registrar un nuevo estudiante"
    )
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        List<EstudianteTo> lista = this.estudianteService.buscarTodos(genero)
            .stream()
            .map(EstudianteMapper::toTo)
            .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(lista).build();
    }

    @POST
    @Path("")
    @Operation(
        summary = "guardar estudiante",
        description = "esta capacidad permite guardar en la ..."
    )
    public void guardar(@RequestBody EstudianteTo estudianteTo) {
        this.estudianteService.guardar(EstudianteMapper.toEntity(estudianteTo));
    }

    @PUT
    @Path("/{id}")
    public void actualizarPorId(@PathParam("id") Integer id, @RequestBody EstudianteTo estudianteTo) {
        var estudiante = EstudianteMapper.toEntity(estudianteTo);
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody EstudianteTo estudianteTo) {
        estudianteTo.setId(id);
        EstudianteTo eTo = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        if(estudianteTo.getApellido() != null){
            eTo.setApellido(estudianteTo.getApellido());
        }
        this.estudianteService.actualizarParcialPorId(EstudianteMapper.toEntity(eTo));
    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
    }

    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id){
        return this.hijoService.buscarPorId(id);
    }
}
