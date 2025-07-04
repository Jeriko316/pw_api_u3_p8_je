package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.ProfesorRepoImpl;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.EstudianteTo;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService{

    @Inject
    private IProfesorRepo profesorRepo;

    @Override
    public ProfesorTo buscarPorId(Integer id, UriInfo uriInfo) {
        // TODO Auto-generated method stub
        Profesor p1 = this.profesorRepo.seleccionarPorId(id);
        ProfesorTo p = new ProfesorTo(p1.getId(), p1.getNombre(), 
        p1.getApellido(), p1.getCedula(), p1.getSalario(), p1.getGenero(), uriInfo);
        return p;
    }

    @Override
    public List<Profesor> buscarTodos(String genero) {
        // TODO Auto-generated method stub
        return this.profesorRepo.seleccionarTodos(genero);
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.profesorRepo.actualizarPorId(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.profesorRepo.actualizarParcialPorId(profesor);
    }

    @Override
    public void borrarPorId(Integer id) {
       this.profesorRepo.borrarPorId(id);
    }

    @Override
    public void guardar(Profesor profesor) {
        this.profesorRepo.insertar(profesor);
    }

}
