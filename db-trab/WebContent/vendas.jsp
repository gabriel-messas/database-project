<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda</title>
<style>
	table, th, td { border: 1px solid black; }
	.column {
    float: left;
    width: 45%;
    }
    .row:after {
    content: "";
    display: table;
    clear: both;
    }
    .border-right {
    border-right: 1px solid black;
	}
	.border-left {
	border-left:  1px solid black;
	}
</style>
</head>
<body>

	<form action="start.jsp">
		<br/>
		<input type="submit" value="Retornar ao Menu" />
		<br/><br/>
	</form>
	
	<hr>
	
	<c:if test="${not empty requestScope.listaVendas}">
	<br/>
		Vendas:
		<br/><br/>
		<table>
			<tr>
				<th> ID </th>
				<th> Cliente </th>
				<th> Produtos </th>
				<th> Valor da Venda </th>
				
			</tr>
			<c:forEach var="venda" items="${requestScope.listaVendas}">
				<tr>
			        <td>
			        	${venda.id}
			        </td>
			        <td>
			        	${venda.cliente.nome}
			        </td>
			        <td>
			        	<c:forEach var="produto" items="${venda.produtos}">
			        		${produto.nome} (Quantidade: ${produto.quantidade} / Preço: ${produto.precoVenda})<br/>
			        	</c:forEach>
			        </td>
			        <td>
			        	${venda.valorVenda}
			        </td>
			       
					<td>
						<a href="/db-trab/excluirVenda?id=${contato.getId()}">Excluir</a>
					</td>
				</tr>
			</c:forEach>		
		</table>
	</c:if>
	
	<br/>
	
	<c:if test="${empty requestScope.listaVendas}">
		Nenhuma venda foi encontrada!
	</c:if>
	
</body>
</html>