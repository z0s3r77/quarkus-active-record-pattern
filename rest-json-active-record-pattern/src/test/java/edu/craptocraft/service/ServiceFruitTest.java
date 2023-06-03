package edu.craptocraft.service;

import edu.craptocraft.model.Fruit;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



@QuarkusTest
@Transactional
class ServiceFruitTest {


    @Inject
    ServiceFruit service;

    // @Test de jupiter, no el de junit
    @Test
    void testList() {
        Assertions.assertThat(service.list()).hasSize(2);
    }

    @Test
    void containsTest() {
        Assertions.assertThat(service.list().stream().anyMatch(f -> f.getName().equals("Apple"))).isTrue();
    }


    @Test
    void addTest() {
        service.add(new Fruit("Banana", "And an attached Gorilla"));
        Assertions.assertThat(service.list()).hasSize(3);
        Assertions.assertThat(service.list().stream().anyMatch(f -> f.getName().equals("Banana"))).isTrue();

        // handmade rollback gracias al antipatron ActiveRecord ;)
        Fruit fruit = Fruit.find("name", "Banana").firstResult();
        fruit.delete();
        Assertions.assertThat(Fruit.count()).isEqualTo(2);
    }


    @Test
    void removeTest(){
        service.remove("Apple");
        Assertions.assertThat(service.list()).hasSize(1);
        Assertions.assertThat(service.list().stream().anyMatch(f -> f.getName().equals("Apple"))).isFalse();

        // handmade rollback gracias al antipatron ActiveRecord ;)
        Fruit.persist(new Fruit("Apple", "Winter fruit"));
        Assertions.assertThat(Fruit.count()).isEqualTo(2);
    }

    @Test
    void getFruitTest() {
        Assertions.assertThat(service.getFruit("Apple")).get().hasFieldOrPropertyWithValue("name", "Apple").hasFieldOrPropertyWithValue("description", "Winter fruit");
        Assertions.assertThat(service.getFruit("Mandarina")).isEmpty();
    }
}
