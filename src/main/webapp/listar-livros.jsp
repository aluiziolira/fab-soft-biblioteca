<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/cabecalho.jsp" />

<h2>Acervo de Livros</h2>

<table>
    <thead>
        <tr>
            <th>Título</th>
            <th>Autor</th>
            <th>Ano</th>
            <th>ISBN</th>
            <th>Ação</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="livro" items="${listaLivros}">
            <tr>
                <td><c:out value="${livro.titulo}" /></td>
                <td><c:out value="${livro.autor}" /></td>
                <td><c:out value="${livro.anoPublicacao}" /></td>
                <td><c:out value="${livro.isbn}" /></td>
                <td>
                    <a href="livros?acao=excluir&isbn=${livro.isbn}"
                       class="btn-excluir"
                       onclick="return confirm('Tem certeza que deseja excluir este livro?');">
                       Excluir
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:if test="${empty listaLivros}">
    <p>Nenhum livro cadastrado no momento.</p>
</c:if>

<jsp:include page="includes/rodape.jsp" />