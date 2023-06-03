package edu.craptocraft.service;


import edu.craptocraft.model.Fruit;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ServiceFruit {

    public ServiceFruit(){}

    public Set<Fruit> list(){
        Stream<Fruit> fruits = Fruit.streamAll();
        return fruits.collect(Collectors.toSet());
    }

    public void add(Fruit fruit){
        fruit.persist();
    }

    public void remove(String name){
        Fruit fruit = Fruit.find("name", name).firstResult();
        fruit.delete();
    }

    public Optional<Fruit> getFruit(String name){
        return name.isBlank() ? Optional.ofNullable(null) : Fruit.find("name", name).firstResultOptional();
    }

}
