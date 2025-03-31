package com.example.ac12.repositories;

import com.example.ac12.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository  extends JpaRepository<Livro, Long> {

    List<Livro> findByPrecoGreaterThan(Double valor);

    List<Livro> findByPrecoLessThanEqual(Double valor);

    List<Livro> findByTituloStartingWith(String titulo);
}
