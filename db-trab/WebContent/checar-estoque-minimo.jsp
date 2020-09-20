<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checar Estoque Mínimo</title>
<style>
	table, th, td { border: 1px solid black; }
</style>
</head>
<body>
	<c:if test="${not empty requestScope.listaEstoqueBaixo}">
		Produtos com Quantidade Baixa em Estoque:
		<br/><br/>
		<table>
			<tr>
				<th> Nome </th>
				<th> Quantidade </th>
				<th> Preço de Venda </th>
				<th> Preço de Compra 1 </th>
				<th> Preço de Compra 2 </th>
			</tr>
			<c:forEach var="produto" items="${requestScope.listaEstoqueBaixo}">
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
				</tr>
			</c:forEach>		
		</table>
	</c:if>
	
	<c:if test="${empty requestScope.listaEstoqueBaixo}">
		Nenhum produto com quantidade menor ou igual a 2 foi encontrado no estoque!
	</c:if>
	
	<form action="start.jsp">
		<br/>
		<input type="submit" value="Retornar ao Menu" />
	</form>
</body>
</html>