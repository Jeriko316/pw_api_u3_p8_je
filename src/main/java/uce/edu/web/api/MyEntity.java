package uce.edu.web.api;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class MyEntity {
    @Id
    @GeneratedValue
    public Long id;

    public String field;
}