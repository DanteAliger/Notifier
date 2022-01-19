package com.notifier.repository;

import com.notifier.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // интерфейс реализует спринг Jpa
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);

}
