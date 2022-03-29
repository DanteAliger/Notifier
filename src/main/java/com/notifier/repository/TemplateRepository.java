package com.notifier.repository;

import com.notifier.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template,Long> {

    Optional<Template> findByName(String nameT);

    void deleteByName(String nameT);

}
