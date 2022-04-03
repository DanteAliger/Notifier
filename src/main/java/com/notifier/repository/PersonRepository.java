package com.notifier.repository;

import com.notifier.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByIdTelegram(Long idTelegram);

    void deleteByIdTelegram(Long idTelegram);

}
