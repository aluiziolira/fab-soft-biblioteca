package br.com.cesumar.biblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    void testGettersAndSetters() {
        // Cria uma instância de Livro
        Livro livro = new Livro();

        // Define os valores usando os setters
        livro.setTitulo("O Senhor dos Anéis");
        livro.setAutor("J.R.R. Tolkien");
        livro.setEditora("HarperCollins");
        livro.setAnoPublicacao(1954);

        // Verifica se os getters retornam os valores corretos
        assertEquals("O Senhor dos Anéis", livro.getTitulo());
        assertEquals("J.R.R. Tolkien", livro.getAutor());
        assertEquals("HarperCollins", livro.getEditora());
        assertEquals(1954, livro.getAnoPublicacao());
    }

    @Test
    void testConstrutor() {
        // Testa o construtor, se houver um que aceite parâmetros
        // Este é um exemplo, ajuste conforme sua implementação
        Livro livro = new Livro("A Arte da Guerra", "Sun Tzu", "Companhia das Letras", 500);

        assertEquals("A Arte da Guerra", livro.getTitulo());
        assertEquals("Sun Tzu", livro.getAutor());
        assertEquals("Companhia das Letras", livro.getEditora());
        assertEquals(500, livro.getAnoPublicacao());
    }
}
