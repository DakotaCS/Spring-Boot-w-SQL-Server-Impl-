package com.example.springsqlwrest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springsqlwrest.model.Tutorial;

public interface tutorialRepo extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
