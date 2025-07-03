package uce.edu.web.api.repository.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prof_id")
    private Integer id;

     @Column(name = "prof_nombre")
    private String nombre;

     @Column(name = "prof_apellido")
    private String apellido;

     @Column(name = "prof_cedula")
    private String cedula;

     @Column(name = "prof_salario")
    private BigDecimal salario;

     @Column(name = "prof_genero")
    private String genero; // Nuevo atributo


    //SET AND GETTERS
     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getNombre() {
         return nombre;
     }

     public void setNombre(String nombre) {
         this.nombre = nombre;
     }

     public String getApellido() {
         return apellido;
     }

     public void setApellido(String apellido) {
         this.apellido = apellido;
     }

     public String getCedula() {
         return cedula;
     }

     public void setCedula(String cedula) {
         this.cedula = cedula;
     }

     public BigDecimal getSalario() {
         return salario;
     }

     public void setSalario(BigDecimal salario) {
         this.salario = salario;
     }

     public String getGenero() {
         return genero;
     }

     public void setGenero(String genero) {
         this.genero = genero;
     }


    

}
