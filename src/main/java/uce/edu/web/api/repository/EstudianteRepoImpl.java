package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;

@Transactional
@ApplicationScoped
public class EstudianteRepoImpl implements IEstudianteRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Estudiante seleccionarPorId(Integer id) {
        // TODO Auto-generated method stub
         return this.entityManager.find(Estudiante.class, id);
    }

    @Override
    public List<Estudiante> seleccionarTodos(String genero) {
        TypedQuery<Estudiante> myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.genero =:genero", Estudiante.class);
        myQuery.setParameter("genero", genero);
        return myQuery.getResultList();
    }

    @Override
    public void actualizarPorId(Estudiante estudiante) {
        // TODO Auto-generated method stub
         this.entityManager.merge(estudiante);
    }


    @Override
    public void actualizarParcialPorId(Estudiante estudiante) {
        // TODO Auto-generated method stub
         this.entityManager.merge(estudiante);
    }

    @Override
    public void borrarPorId(Integer id) {
        // TODO Auto-generated method stub
       this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(Estudiante estudiante) {
        // TODO Auto-generated method stub
            this.entityManager.persist(estudiante);
    }
 
}

