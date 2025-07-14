package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService {

    @Inject
    private IProfesorRepo profesorRepo;

    @Override
    public List<Profesor> buscarTodos(String genero) {
        return this.profesorRepo.seleccionarTodos(genero);
    }

    @Override
    public void guardar(Profesor profesor) {
        this.profesorRepo.insertar(profesor);
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
    public ProfesorTo buscarPorId(Integer id, UriInfo uriInfo) {
        Profesor profesor = this.profesorRepo.seleccionarPorId(id);
        if (profesor == null) return null;
        return ProfesorMapper.toTo(profesor); // Pasa uriInfo si tu TO lo necesita
    }
}
