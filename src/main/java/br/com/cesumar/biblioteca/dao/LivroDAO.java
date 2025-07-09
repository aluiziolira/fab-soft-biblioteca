package br.com.cesumar.biblioteca.dao;

import br.com.cesumar.biblioteca.model.Livro;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * DAO (Data Access Object) para Livros.
 * Simula um banco de dados em memória usando uma lista estática.
 * O uso de 'CopyOnWriteArrayList' garante segurança em ambientes com múltiplas threads (como um servidor web).
 */
public class LivroDAO {

    // Lista estática para armazenar os livros, atuando como nosso "banco de dados"
    private static final List<Livro> acervo = new CopyOnWriteArrayList<>();

    // Bloco estático para popular a lista com alguns dados iniciais
    static {
        acervo.add(new Livro("Engenharia de Software", "Ian Sommerville", 2011, "9788576059868"));
        acervo.add(new Livro("Código Limpo", "Robert C. Martin", 2009, "9788576082675"));
        acervo.add(new Livro("Use a Cabeça! Java", "Kathy Sierra", 2007, "9788576081739"));
    }

    /**
     * Retorna uma cópia da lista de todos os livros no acervo.
     * @return uma lista de objetos Livro.
     */
    public List<Livro> listarTodos() {
        return new ArrayList<>(acervo);
    }

    /**
     * Adiciona um novo livro ao acervo.
     * @param livro O objeto Livro a ser adicionado.
     */
    public void adicionar(Livro livro) {
        if (livro != null) {
            acervo.add(livro);
        }
    }

    /**
     * Remove um livro do acervo com base no seu ISBN.
     * @param isbn O ISBN do livro a ser removido.
     */
    public void excluir(String isbn) {
        if (isbn == null) {
            return; // Não faz nada se o ISBN for nulo
        }
        // Usa o método removeIf com uma expressão lambda para encontrar e remover o livro
        acervo.removeIf(livro -> livro != null && isbn.equals(livro.getIsbn()));
    }

    /**
     * Verifica se um livro com o mesmo ISBN já existe.
     * @param isbn O ISBN a ser verificado.
     * @return true se o ISBN já existir, false caso contrário.
     */
    public boolean isbnJaExiste(String isbn) {
        if (isbn == null) {
            return false; // ISBN nulo não pode existir
        }
        for (Livro livro : acervo) {
            if (livro != null && livro.getIsbn() != null && livro.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }
}