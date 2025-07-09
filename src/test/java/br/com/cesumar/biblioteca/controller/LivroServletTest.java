package br.com.cesumar.biblioteca.controller;

import br.com.cesumar.biblioteca.dao.LivroDAO;
import br.com.cesumar.biblioteca.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServletTest {

    @Mock
    private LivroDAO livroDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private LivroServlet livroServlet;

    @BeforeEach
    void setUp() {
        // Comportamento padrão para o mock do RequestDispatcher
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    void testDoGetListarAcaoPadrao() throws ServletException, IOException {
        // Arrange: Simula uma requisição sem o parâmetro 'acao'
        when(request.getParameter("acao")).thenReturn(null);
        List<Livro> livros = new ArrayList<>();
        when(livroDAO.listarTodos()).thenReturn(livros);

        // Act
        livroServlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("listaLivros", livros);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoGetListarAcaoExplicita() throws ServletException, IOException {
        // Arrange: Simula uma requisição com 'acao=listar'
        when(request.getParameter("acao")).thenReturn("listar");
        List<Livro> livros = new ArrayList<>();
        when(livroDAO.listarTodos()).thenReturn(livros);

        // Act
        livroServlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("listaLivros", livros);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoGetExcluir() throws ServletException, IOException {
        // Arrange: Simula uma requisição com 'acao=excluir'
        when(request.getParameter("acao")).thenReturn("excluir");
        when(request.getParameter("isbn")).thenReturn("123");

        // Act
        livroServlet.doGet(request, response);

        // Assert
        verify(livroDAO).excluir("123");
        verify(response).sendRedirect("livros?acao=listar");
    }
    
    @Test
    void testDoGetAcaoInvalida() throws ServletException, IOException {
        // Arrange: Simula uma requisição com uma ação desconhecida, deve cair no default (listar)
        when(request.getParameter("acao")).thenReturn("acao_desconhecida");
        List<Livro> livros = new ArrayList<>();
        when(livroDAO.listarTodos()).thenReturn(livros);

        // Act
        livroServlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("listaLivros", livros);
        verify(dispatcher).forward(request, response);
    }
}
