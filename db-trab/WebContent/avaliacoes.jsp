<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Avaliações</title>
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
	
	<c:if test="${not empty sessionScope.listaAvaliacoes}">
	<br/>
		Avaliações:
		<br/><br/>
		<table>
			<tr>
				<th> ID </th>
				<th> Data da Entrega </th>
				<th> Cliente </th>
				<th> Nota </th>
				<th> Observação </th>
			</tr>
			<c:forEach var="avaliacao" items="${sessionScope.listaAvaliacoes}">
				<jsp:useBean id="classVenda" class="trab.VendaDAO"/>
				<jsp:useBean id="classVenda2" class="trab.ContVendaDAO"/>
				<jsp:useBean id="classEntrega" class="trab.EntregaDAO"/>
				<jsp:useBean id="classAvaliacao" class="trab.AvaliacaoDAO"/>
				
				<c:set var="entrega" value="${classEntrega.buscarPorIdEntrega(avaliacao.id_entrega)}" />
				<c:set var="cliente" value="${classVenda2.buscarPorIdCliente(entrega.id_venda)}" />
				<tr>
			        <td>
			        	${avaliacao.id}
			        </td>
			        <td>
			        	${ entrega.dataEntrega }
			        </td>
			        <td>
			        	${ cliente.nome }
			        </td>
			        <td>
			        	${avaliacao.nota}
			        </td>
			        <td>
			        	${avaliacao.observacao}
			        </td>
				</tr>
			</c:forEach>		
		</table>
	</c:if>
	
	<br/>
	
	<c:if test="${empty sessionScope.listaAvaliacoes}">
		Nenhuma avaliação foi encontrada!
	</c:if>
	
</body>
</html>