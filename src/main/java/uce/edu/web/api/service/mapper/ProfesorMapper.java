package uce.edu.web.api.service.mapper;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public class ProfesorMapper {

    /*public static ProfesorTo toTo(ProfesorTo profesor) {
        if (profesor == null) return null;
        // Puedes pasar null en uriInfo si no lo necesitas, o agregarlo en el controlador
        return new ProfesorTo(
            profesorTo.getId(),
            profesor.getNombre(),
            profesorTo.getApellido(),
            profesor.getCedula(),
            profesorTo.getSalario(),
            profesor.getGenero(),
            null // El controlador puede pasar el UriInfo real si lo requiere
        );
    }

    public static Profesor toEntity(ProfesorTo profesorTo) {
        Profesor p = new Profesor();
        p.setId(profesorTo.getId());
        p.setNombre(profesorTo.getNombre());
        p.setApellido(profesorTo.getApellido());
        p.setCedula(profesorTo.getCedula());
        p.setSalario(profesorTo.getSalario());
        p.setGenero(profesorTo.getGenero());
        return p;
    }

    public static List<ProfesorTo> toToList(List<Profesor> profesores, UriInfo uriInfo) {
        List<ProfesorTo> resultList = new ArrayList<>();
        for (Profesor profesor : profesores) {
            resultList.add(new ProfesorTo(
                profesor.getId(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getCedula(),
                profesor.getSalario(),
                profesor.getGenero(),
                uriInfo
            ));
        }
        return resultList;
    }*/
}
