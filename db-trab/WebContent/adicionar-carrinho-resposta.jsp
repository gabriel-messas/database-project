<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produto Adicionado ao Carrinho</title>
</head>
<body>
<jsp:useBean id="produto" class="trab.AdicionarCarrinhoServlet"/>
O produto ${produto.produto.nome} foi adicionado ao carrinho com sucesso!

	<form action="buscar-produto.jsp">
		<br/>
		<input type="submit" value="Continuar Adicionando" />
	</form>
	
	<form action="buscarClientes">
		<br/>
		<input type="submit" value="Selecionar Cliente" />
	</form>
	
	<form action="finalizarVenda">
		<br/>
		<input type="submit" value="Finalizar Venda" />
	</form>
</body>
</html>