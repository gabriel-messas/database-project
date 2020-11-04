<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vendas</title>
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
	
	<c:if test="${not empty sessionScope.listaVendas}">
	<br/>
		Vendas:
		<br/><br/>
		<table>
			<tr>
				<th> ID </th>
				<th> Data </th>
				<th> Quantidade </th>
				<th> Valor da Venda </th>
				<th> Cliente </th>
				<th> Funcionário </th>
			</tr>
			<c:forEach var="venda" items="${sessionScope.listaVendas}">
				<jsp:useBean id="classVenda" class="trab.BuscarVendaServlet"/>
				<tr>
			        <td>
			        	${venda.id}
			        </td>
			        <td>
			        	${venda.data}
			        </td>
			        <td>
			        	${venda.quantidade}
			        </td>
			        <td>
			        	${venda.valorVenda}
			        </td>
			        <td>
			        	${classVenda.getCliente(venda).nome}
			        </td>
			        <td>
			        	${classVenda.getFuncionario(venda).nome}
			        </td>
			        <td>
			       	<c:forEach var="produto" items="${classVenda.getProdutos(venda)}">
			       		${produto.nome} (${produto.quantidade})<br/>
			       	</c:forEach>
			       	</td>
					<td>
						<a href="/db-trab/excluirVenda?id=${venda.getId()}">Excluir</a>
					</td>
				</tr>
			</c:forEach>		
		</table>
	</c:if>
	
	<br/>
	
	<c:if test="${empty sessionScope.listaVendas}">
		Nenhuma venda foi encontrada!
	</c:if>
	
</body>
</html>