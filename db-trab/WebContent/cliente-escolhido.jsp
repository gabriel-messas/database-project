<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente Escolhido</title>
</head>
<body>
<jsp:useBean id="cliente" class="trab.SelecionarClienteServlet"/>

O cliente ${cliente.cliente.nome} foi selecionado com sucesso!

	<form action="finalizarVenda">
		<br/>
		<input type="submit" value="Finalizar Venda" />
	</form>
	
	<form action="buscarFuncionarios">
		<br/>
		<input type="submit" value="Selecionar Funcionario" />
	</form>
	
	<form action="buscar-produto.jsp">
		<br/>
		<input type="submit" value="Adicionar Produtos ao Carrinho" />
	</form>
</body>
</html>