package com.example.ac12;

import java.util.List;
import java.util.Optional;

import com.example.ac12.models.Autor;
import com.example.ac12.models.Livro;
import com.example.ac12.repositories.AutorRepository;
import com.example.ac12.repositories.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ac12Application {

	public static void main(String[] args) {
		SpringApplication.run(Ac12Application.class, args);
	}

	@Bean
	public CommandLineRunner demo1(LivroRepository repository) {
		return (args) -> {
			Livro livro1 = new Livro("A Guerra dos Tronos", 49.90);
			Livro livro2 = new Livro("O Senhor dos Anéis", 59.90);
			Livro livro3 = new Livro("A Dança dos Dragões", 69.90);
			Livro livro4 = new Livro("O Pequeno Príncipe", 29.90);
			Livro livro5 = new Livro("Harry Potter", 35.50);

			repository.save(livro1);
			repository.save(livro2);
			repository.save(livro3);
			repository.save(livro4);
			repository.save(livro5);

			System.out.println("\n\n\n\n\n--- Parte 1: ---");
			System.out.println("Livros inseridos com sucesso!");
			System.out.println("----------------------------");

			double valorMaiorQue = 50.0;
			List<Livro> livrosMaiorQue = repository.findByPrecoGreaterThan(valorMaiorQue);
			System.out.println("Livros com preço maior que " + valorMaiorQue + ":");
			livrosMaiorQue.forEach(livro -> System.out.println(livro.getTitulo() + " - R$" + livro.getPreco()));
			System.out.println("----------------------------");

			double valorMenorOuIgualA = 40.0;
			List<Livro> livrosMenorOuIgualA = repository.findByPrecoLessThanEqual(valorMenorOuIgualA);
			System.out.println("Livros com preço menor ou igual a " + valorMenorOuIgualA + ":");
			livrosMenorOuIgualA.forEach(livro -> System.out.println(livro.getTitulo() + " - R$" + livro.getPreco()));
			System.out.println("----------------------------");

			String textoInicial = "A ";
			List<Livro> livrosComecandoComA = repository.findByTituloStartingWith(textoInicial);
			System.out.println("Livros cujo título começa com '" + textoInicial + "':");
			livrosComecandoComA.forEach(livro -> System.out.println(livro.getTitulo() + " - R$" + livro.getPreco()));
			System.out.println("----------------------------");
		};
	}

	@Bean
	public CommandLineRunner demoAutoresComLivros(AutorRepository autorRepository, LivroRepository livroRepository) {
		return args -> {
			System.out.println("\n\n\n\n --- Parte 2: ---");
			System.out.println("\n--- Inserindo Autores e Livros ---");

			Autor autor1 = new Autor("George R.R. Martin");
			Autor autor2 = new Autor("J.R.R. Tolkien");

			Livro livro1_1 = new Livro("A Guerra dos Tronos", 49.90);
			Livro livro1_2 = new Livro("A Fúria dos Reis", 54.90);
			autor1.adicionarLivro(livro1_1);
			autor1.adicionarLivro(livro1_2);

			Livro livro2_1 = new Livro("O Hobbit", 39.90);
			Livro livro2_2 = new Livro("O Senhor dos Anéis", 69.90);
			Livro livro2_3 = new Livro("O Silmarillion", 59.90);
			autor2.adicionarLivro(livro2_1);
			autor2.adicionarLivro(livro2_2);
			autor2.adicionarLivro(livro2_3);

			autorRepository.save(autor1);
			autorRepository.save(autor2);

			System.out.println("Autores e livros associados inseridos com sucesso!");
			System.out.println("----------------------------------");

			String nomeComecaCom = "George";
			List<Autor> autoresComecandoCom = autorRepository.findByNomeStartingWith(nomeComecaCom);
			System.out.println("\nAutores cujo nome começa com '" + nomeComecaCom + "':");
			autoresComecandoCom.forEach(autor -> {
				System.out.println("ID: " + autor.getId() + ", Nome: " + autor.getNome());
			});
			System.out.println("----------------------------------");

			Long autorIdParaBuscar = autor2.getId();
			Optional<Autor> autorEncontrado = autorRepository.findAutorComLivros(autorIdParaBuscar);

			System.out.println("\nAutor com ID " + autorIdParaBuscar + " e seus livros:");
			autorEncontrado.ifPresentOrElse(autor -> {
				System.out.println("ID: " + autor.getId() + ", Nome: " + autor.getNome());
				autor.getLivros().forEach(livro -> System.out.println("- ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Preço: R$" + livro.getPreco()));
			}, () -> System.out.println("Autor com ID " + autorIdParaBuscar + " não encontrado."));
			System.out.println("----------------------------------");

			Optional<Autor> autorEncontradoSemFetch = autorRepository.findById(autorIdParaBuscar);
			System.out.println("\nAutor com ID " + autorIdParaBuscar + " (sem FETCH):");
			autorEncontradoSemFetch.ifPresentOrElse(autor -> {
				System.out.println("ID: " + autor.getId() + ", Nome: " + autor.getNome());
				System.out.println("- Livros (podem ser carregados lazy): " + autor.getLivros());
			}, () -> System.out.println("Autor com ID " + autorIdParaBuscar + " não encontrado."));
			System.out.println("----------------------------------");
		};
	}

}
