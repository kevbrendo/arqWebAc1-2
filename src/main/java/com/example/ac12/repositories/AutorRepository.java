package com.example.ac12.repositories;

import com.example.ac12.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNomeStartingWith(String nome);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.livros WHERE a.id = :id")
    Optional<Autor> findAutorComLivros(@Param("id") Long id);
}
