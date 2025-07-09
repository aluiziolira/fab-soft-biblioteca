package br.com.cesumar.biblioteca.dao;

import br.com.cesumar.biblioteca.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivroDAOTest {

    private LivroDAO livroDAO;

    @BeforeEach
    void setUp() {
        // Reinicia o DAO para garantir que os testes sejam independentes
        livroDAO = new LivroDAO();
        // Limpa o acervo antes de cada teste para evitar interferência dos dados estáticos
        livroDAO.listarTodos().forEach(livro -> livroDAO.excluir(livro.getIsbn()));
        // Adiciona dados de teste conhecidos
        livroDAO.adicionar(new Livro("Livro Teste 1", "Autor 1", 2020, "111"));
        livroDAO.adicionar(new Livro("Livro Teste 2", "Autor 2", 2021, "222"));
    }

    @Test
    void testListarTodos() {
        List<Livro> livros = livroDAO.listarTodos();
        assertNotNull(livros);
        assertEquals(2, livros.size());
    }

    @Test
    void testAdicionar() {
        Livro novoLivro = new Livro("Livro Teste 3", "Autor 3", 2022, "333");
        livroDAO.adicionar(novoLivro);

        List<Livro> livros = livroDAO.listarTodos();
        assertEquals(3, livros.size());
        assertTrue(livros.contains(novoLivro));
    }

    @Test
    void testExcluir() {
        livroDAO.excluir("111");
        List<Livro> livros = livroDAO.listarTodos();
        assertEquals(1, livros.size());
        assertFalse(livroDAO.isbnJaExiste("111"));
    }

    @Test
    void testExcluirIsbnInexistente() {
        livroDAO.excluir("999"); // Tenta excluir um livro que não existe
        List<Livro> livros = livroDAO.listarTodos();
        assertEquals(2, livros.size()); // O tamanho da lista não deve mudar
    }

    @Test
    void testIsbnJaExiste() {
        assertTrue(livroDAO.isbnJaExiste("111"));
        assertTrue(livroDAO.isbnJaExiste("222"));
    }

    @Test
    void testIsbnNaoExiste() {
        assertFalse(livroDAO.isbnJaExiste("999"));
    }
}
