package br.com.cesumar.biblioteca.controller;

import br.com.cesumar.biblioteca.dao.LivroDAO;
import br.com.cesumar.biblioteca.model.Livro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que controla as ações de listagem e exclusão de livros.
 * Atua como o Controller principal para as operações baseadas em JSP.
 */
@WebServlet("/livros")
public class LivroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final LivroDAO livroDAO = new LivroDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "listar"; // Ação padrão
        }

        switch (acao) {
            case "excluir":
                excluirLivro(request, response);
                break;
            case "listar":
            default:
                listarLivros(request, response);
                break;
        }
    }

    private void listarLivros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Busca a lista de livros do DAO
        List<Livro> listaDeLivros = livroDAO.listarTodos();

        // Adiciona a lista ao objeto request para que a JSP possa acessá-la
        request.setAttribute("listaLivros", listaDeLivros);

        // Encaminha a requisição para a página JSP de listagem
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listar-livros.jsp");
        dispatcher.forward(request, response);
    }

    private void excluirLivro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Pega o ISBN do livro a ser excluído a partir do parâmetro da URL
        String isbn = request.getParameter("isbn");

        // Chama o método do DAO para remover o livro
        livroDAO.excluir(isbn);

        // Redireciona de volta para a página de listagem
        response.sendRedirect("livros?acao=listar");
    }
}