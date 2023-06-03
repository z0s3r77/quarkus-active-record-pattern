package edu.craptocraft.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Fruit")
@JsonbPropertyOrder({"description","name"})
public class Fruit extends PanacheEntity {

    @NotBlank
    @Column(unique = true)
    public String name;

    @NotEmpty
    @Column
    public String description;

    public Fruit(){}

    public Fruit(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}
