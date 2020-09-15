<%@ page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	table, th, td { border: 1px solid black; }
</style>
<title>Buscar Produto</title>
</head>
<body>
	<form action="buscar">
		Digite o nome do produto: 
		<input type="text" name="nome" />
		<br/>
		<br/>
		<input type="submit" value="Buscar" />
	</form>
	
	<br/>
	
	<form action="start.jsp">
		<input type="submit" value="Voltar" />
	</form>
	
	<br/>
		
	<c:if test="${not empty sessionScope.listaRecentes}">		
		Lista de buscas recentes:
		<table>
			<tr>
				<th> Nome </th>
				<th> Quantidade </th>
				<th> Preço </th>
			</tr>
			<c:forEach var="produto" items="${sessionScope.listaRecentes}">
				<tr>
			        <td>
			        	${produto.nome}
			        </td>
			        <td>
			        	${produto.quantidade}
			        </td>
			        <td>
			        	${produto.preco}
			        </td>
			        <td>
						<a href="/trab/alterar?id=${produto.getId()}">Alterar</a>
					</td>
					<td>
						<a href="/trab/excluir?id=${produto.getId()}">Excluir</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	
		
</body>
</html>