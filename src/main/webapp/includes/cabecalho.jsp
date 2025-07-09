<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Biblioteca Cesumar</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }
        .container { width: 80%; margin: auto; overflow: hidden; }
        header { background: #003366; color: #ffffff; padding: 20px 0; border-bottom: #0056b3 3px solid; }
        header a { color: #ffffff; text-decoration: none; text-transform: uppercase; font-size: 16px; }
        header nav { float: right; margin-top: 10px; }
        header nav a { padding: 0 20px; }
        header .branding { float: left; }
        header .branding h1 { margin: 0; }
        .main { padding: 20px; }
        footer { background: #003366; color: #ffffff; text-align: center; padding: 20px; margin-top: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #0056b3; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn-excluir { color: #dc3545; text-decoration: none; }
        .btn-excluir:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <header>
        <div class="container">
            <div class="branding">
                <h1><a href="index.jsp">Biblioteca Cesumar</a></h1>
            </div>
            <nav>
                <a href="livros?acao=listar">Listar Livros</a>
                <a href="cadastrar-livro.xhtml">Novo Cadastro (JSF)</a>
            </nav>
        </div>
    </header>
    <div class="container main">