<%@page import="trab.Produto"%>
<%@page import="java.util.List"%>
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
<title>Resposta da busca</title>
</head>
<body>
	
	<c:if test="${not empty requestScope.listaProdutos}">
		Resultado da busca:
		<table>
			<tr>
				<th> Nome </th>
				<th> Quantidade </th>
				<th> Preço de Venda </th>
				<th> Preço de Compra 1 </th>
				<th> Preço de Compra 2 </th>
			</tr>
			<c:forEach var="produto" items="${requestScope.listaProdutos}">
				<tr>
			        <td>
			        	${produto.nome}
			        </td>
			        <td>
			        	${produto.quantidade}
			        </td>
			        <td>
			        	${produto.precoVenda}
			        </td>
			        <td>
			        	${produto.precoCompra1}
			        </td>
			        <td>
			        	${produto.precoCompra2}
			        </td>
			        <td>
						<a href="/db-trab/alterar?id=${produto.getId()}">Alterar</a>
					</td>
					<td>
						<a href="/db-trab/excluir?id=${produto.getId()}">Excluir</a>
					</td>
					<td>
						<a href="/db-trab/addCarrinho.jsp?pid=${produto.getId()}&pnome=${produto.getNome()}&pquantidade=${produto.getQuantidade()}&ppreco=${produto.getPrecoVenda()}">Adicionar para Venda</a>
					</td>
				</tr>
			</c:forEach>		
		</table>
	</c:if>
	
	<c:if test="${empty requestScope.listaProdutos}">
		Nenhum produto foi encontrado!
	</c:if>
	
	<form action="buscar-produto.jsp">
		<br/>
		<input type="submit" value="Retornar à Busca" />
	</form>
</body>
</html>