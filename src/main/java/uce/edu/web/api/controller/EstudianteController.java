package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
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
public class EstudianteController  extends BaseControlador{

   @Inject
    private IEstudianteService estudianteService;

    @Inject
    private IHijoService hijoService;
 
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
        return Response.status(227).entity(estu).build();
    }
 
    //?genero=F&provincia=pichincha  SOAP -> XML     RESTFUL-> JSON
    @GET
    @Path("")
    @Operation(
        summary = "consultar estudiante",
        description = "este end point permite registrar un nuevo estudiante"
    )
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
    }
 
    @POST
    @Path("")
    @Operation(
        summary = "guardar estudiante",
        description = "esta capacidad permite guardar en la ..."
    )
    //Puede tener o no tener el @RequestBody
    public void guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
       
    }
 
    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, @RequestBody Estudiante estudiante) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
    }
 
   /*  @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody Estudiante estudiante) {
        estudiante.setId(id);
        Estudiante e=this.estudianteService.buscarPorId(id);
        if(estudiante.getNombre() != null) {
            e.setNombre(estudiante.getNombre());
        }
        if(estudiante.getApellido() != null) {
            e.setApellido(estudiante.getApellido());
        }
        if(estudiante.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
 
        this.estudianteService.actualizarParcialPorId(e);
    }*/
 
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
