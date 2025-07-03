package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Profesor;

@Transactional
@ApplicationScoped
public class ProfesorRepoImpl implements IProfesorRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Profesor seleccionarPorId(Integer id) {
        // TODO Auto-generated method stub
        return this.entityManager.find(Profesor.class, id);
    }

    @Override
    public List<Profesor> seleccionarTodos(String genero) {
        TypedQuery <Profesor> mQuery = this.entityManager.createQuery("SELECT p FROM Profesor p", Profesor.class);
        return mQuery.getResultList();
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
       this.entityManager.merge(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        // TODO Auto-generated method stub
        this.entityManager.merge(profesor);
    }

    @Override
    public void borrarPorId(Integer id) {
        // TODO Auto-generated method stub
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(Profesor profesor) {
        // TODO Auto-generated method stub
        this.entityManager.persist(profesor);
    }

}
