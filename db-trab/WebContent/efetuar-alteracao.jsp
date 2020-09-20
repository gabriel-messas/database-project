<%@page import="trab.Produto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Efetuar Alteração</title>
</head>
<body>
	<jsp:useBean id="produto" class="trab.AlterarProdutoServlet"/>

	<form action="efetuarAlteracao" method="POST">
		Nome: <input type="text" name="nome" value="${produto.lastProdutoAlterado.nome}"/> <br />
		Preço de Compra 1: <input type="text" name="precoCompra1" value="${produto.lastProdutoAlterado.precoCompra1}"/> <br />
		Preço de Compra 2: <input type="text" name="precoCompra2" value="${produto.lastProdutoAlterado.precoCompra2}"/> <br />
		Preço de Venda: <input type="text" name="precoVenda" value="${produto.lastProdutoAlterado.precoVenda}"/> <br />
		Quantidade: <input type="text" name="quantidade" value="${produto.lastProdutoAlterado.quantidade}"/> <br />
		<input type="hidden" name=id value="${produto.lastProdutoAlterado.id}"/> <br />
		<input type="submit" value="Alterar" />
	</form>
	
	<form action="buscar-produto.jsp">
		<br/>
		<input type="submit" value="Cancelar" />
	</form>
</body>
</html>