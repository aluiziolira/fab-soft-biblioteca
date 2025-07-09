package br.com.cesumar.biblioteca.controller;

import br.com.cesumar.biblioteca.dao.LivroDAO;
import br.com.cesumar.biblioteca.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroBeanTest {

    @Mock
    private LivroDAO livroDAO;

    @Mock
    private FacesContext facesContext;

    @InjectMocks
    private LivroBean livroBean;

    @BeforeEach
    void setUp() {
        // Injeta o mock do FacesContext. Este é um padrão comum para testar JSF beans.
        lenient().when(facesContext.isPostback()).thenReturn(false);
        lenient().doNothing().when(facesContext).addMessage(anyString(), any(FacesMessage.class));
    }

    @Test
    void testCadastroComSucesso() {
        // Arrange: Configura os dados de entrada e o comportamento do mock
        livroBean.setTitulo("Teste de Título");
        livroBean.setAutor("Teste de Autor");
        livroBean.setAnoPublicacao(2023);
        livroBean.setIsbn("12345");
        when(livroDAO.isbnJaExiste("12345")).thenReturn(false);

        // Act: Executa o método a ser testado
        String resultado = livroBean.cadastrar();

        // Assert: Verifica o resultado
        assertEquals("sucesso", resultado);
        // Verifica se o método adicionar do DAO foi chamado exatamente uma vez com qualquer objeto Livro
        verify(livroDAO, times(1)).adicionar(any(Livro.class));
    }

    @Test
    void testCadastroComCamposVazios() {
        // Teste com título nulo
        livroBean.setTitulo(null);
        String resultado = livroBean.cadastrar();
        assertNull(resultado);
        verify(facesContext, times(1)).addMessage(eq(null), any(FacesMessage.class));

        // Teste com autor vazio
        livroBean.setTitulo("Título Válido");
        livroBean.setAutor(" "); // Espaço em branco
        resultado = livroBean.cadastrar();
        assertNull(resultado);
        verify(facesContext, times(2)).addMessage(eq(null), any(FacesMessage.class));
    }

    @Test
    void testCadastroComIsbnDuplicado() {
        // Arrange
        livroBean.setTitulo("Outro Título");
        livroBean.setAutor("Outro Autor");
        livroBean.setAnoPublicacao(2024);
        livroBean.setIsbn("54321");
        when(livroDAO.isbnJaExiste("54321")).thenReturn(true);

        // Act
        String resultado = livroBean.cadastrar();

        // Assert
        assertNull(resultado);
        verify(livroDAO, never()).adicionar(any(Livro.class)); // Garante que o livro não foi adicionado
        verify(facesContext, times(1)).addMessage(eq(null), any(FacesMessage.class));
    }
    
    @Test
    void testGettersAndSetters() {
        livroBean.setTitulo("Título");
        assertEquals("Título", livroBean.getTitulo());

        livroBean.setAutor("Autor");
        assertEquals("Autor", livroBean.getAutor());

        livroBean.setAnoPublicacao(2000);
        assertEquals(2000, livroBean.getAnoPublicacao());

        livroBean.setIsbn("ISBN");
        assertEquals("ISBN", livroBean.getIsbn());
    }
}
