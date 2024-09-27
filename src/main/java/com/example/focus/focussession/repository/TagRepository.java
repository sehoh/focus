package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositorySupport {
    Optional<Tag> findByName(String name);

}
