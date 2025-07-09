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
        livro.setAnoPublicacao(1954);
        livro.setIsbn("978-3-16-148410-0");

        // Verifica se os getters retornam os valores corretos
        assertEquals("O Senhor dos Anéis", livro.getTitulo());
        assertEquals("J.R.R. Tolkien", livro.getAutor());
        assertEquals(1954, livro.getAnoPublicacao());
        assertEquals("978-3-16-148410-0", livro.getIsbn());
    }

    @Test
    void testConstrutorComParametros() {
        // Testa o construtor com a assinatura correta
        Livro livro = new Livro("A Arte da Guerra", "Sun Tzu", 500, "978-85-359-0223-8");

        assertEquals("A Arte da Guerra", livro.getTitulo());
        assertEquals("Sun Tzu", livro.getAutor());
        assertEquals(500, livro.getAnoPublicacao());
        assertEquals("978-85-359-0223-8", livro.getIsbn());
    }
    
    @Test
    void testToString() {
        Livro livro = new Livro("Código Limpo", "Robert C. Martin", 2009, "978-85-7608-267-5");
        String expected = "Livro{titulo='Código Limpo', autor='Robert C. Martin', anoPublicacao=2009, isbn='978-85-7608-267-5'}";
        assertEquals(expected, livro.toString());
    }

    @Test
    void testDefaultConstructor() {
        Livro livro = new Livro();
        assertNotNull(livro);
        assertNull(livro.getTitulo());
        assertNull(livro.getAutor());
        assertEquals(0, livro.getAnoPublicacao());
        assertNull(livro.getIsbn());
    }
}