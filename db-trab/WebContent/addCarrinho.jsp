<%@page import="trab.Produto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar ao Carrinho</title>
</head>
<body>
	
	<form action="addCarrinho" method="POST">
		Nome: <input type="text" name="nome" value="${param.pnome}"/> <br />
		Quantidade: <input type="number" name="quantidade" value="${param.pquantidade}" max="${param.pquantidade}"/> <br />
		Preço Unitário: <input type="number" name="preco" value="${param.ppreco}" readonly/> <br />
		<input type="hidden" name=id value="${param.pid}"/> <br />
		<input type="submit" value="Adicionar" />
	</form>
	
	<form action="buscar-produto.jsp">
		<br/>
		<input type="submit" value="Cancelar" />
	</form>
</body>
</html>