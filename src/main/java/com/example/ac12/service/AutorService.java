package com.example.ac12.service;

import com.example.ac12.models.Autor;
import com.example.ac12.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> buscarAutoresComecandoCom(String nome) {
        return autorRepository.findByNomeStartingWith(nome);
    }

    public Optional<Autor> buscarAutorComLivros(Long id) {
        return autorRepository.findAutorComLivros(id);
    }
}
