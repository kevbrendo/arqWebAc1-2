package com.example.ac12.service;

import com.example.ac12.models.Livro;
import com.example.ac12.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> buscarLivrosComPrecoMaiorQue(Double valor) {
        return livroRepository.findByPrecoGreaterThan(valor);
    }

    public List<Livro> buscarLivrosComPrecoMenorOuIgualA(Double valor) {
        return livroRepository.findByPrecoLessThanEqual(valor);
    }

    public List<Livro> buscarLivrosComTituloIniciandoCom(String titulo) {
        return livroRepository.findByTituloStartingWith(titulo);
    }
}
