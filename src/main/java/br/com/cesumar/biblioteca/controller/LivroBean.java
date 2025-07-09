package br.com.cesumar.biblioteca.controller;

import br.com.cesumar.biblioteca.dao.LivroDAO;
import br.com.cesumar.biblioteca.model.Livro;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Managed Bean do JSF para a funcionalidade de cadastro de livros.
 * Este é o Controller para a nossa View JSF (cadastrar-livro.xhtml).
 */
@ManagedBean
@RequestScoped
public class LivroBean {

    // Atributos que serão ligados ('bound') aos componentes da tela JSF
    private String titulo;
    private String autor;
    private Integer anoPublicacao;
    private String isbn;

    private final LivroDAO livroDAO = new LivroDAO();

    // Getters e Setters para os atributos do formulário
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    /**
     * Ação executada quando o formulário de cadastro é enviado.
     * @return Uma string de navegação para o JSF. "sucesso" redireciona para a listagem.
     */
    public String cadastrar() {
        // Tratamento de erros para campos vazios
        if (titulo == null || titulo.trim().isEmpty() ||
            autor == null || autor.trim().isEmpty() ||
            isbn == null || isbn.trim().isEmpty() ||
            anoPublicacao == null) {

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação", "Todos os campos são obrigatórios."));
            return null; // Permanece na mesma página
        }

        // Tratamento de erro para ISBN duplicado
        if (livroDAO.isbnJaExiste(isbn)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação", "Já existe um livro com este ISBN."));
            return null; // Permanece na mesma página
        }

        // Se a validação passar, cria um novo objeto Livro
        Livro novoLivro = new Livro(titulo, autor, anoPublicacao, isbn);

        // Adiciona o livro usando o DAO
        livroDAO.adicionar(novoLivro);

        // Retorna a "outcome" de navegação definida no faces-config.xml
        return "sucesso";
    }
}