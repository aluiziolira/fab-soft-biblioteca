package br.com.cesumar.biblioteca.model;

/**
 * Classe Model que representa um Livro.
 * Esta é uma classe POJO (Plain Old Java Object) com os atributos do livro.
 */
public class Livro {

    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String isbn;

    // Construtor padrão (necessário para o JSF Bean)
    public Livro() {
    }

    // Construtor com parâmetros para facilitar a criação de objetos
    public Livro(String titulo, String autor, int anoPublicacao, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Sobrescrevendo o método toString para facilitar a depuração
    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}