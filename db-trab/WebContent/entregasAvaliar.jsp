<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Entregas</title>
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
	
	<c:if test="${not empty sessionScope.listaEntregas}">
	<br/>
		Entregas:
		<br/><br/>
		<table>
			<tr>
				<th> ID </th>
				<th> ID da Venda </th>
				<th> Data Prevista para Entrega </th>
				<th> Data Real da Entrega </th>
				<th> Cliente </th>
				<th> Valor da Venda </th>
			</tr>
			<c:forEach var="entrega" items="${sessionScope.listaEntregas}">
				<jsp:useBean id="classVenda" class="trab.VendaDAO"/>
				<jsp:useBean id="classVenda2" class="trab.ContVendaDAO"/>
				<jsp:useBean id="classEntrega" class="trab.EntregaDAO"/>
				<jsp:useBean id="classAvaliacao" class="trab.AvaliacaoDAO"/>
				<tr>
			        <td>
			        	${entrega.id}
			        </td>
			        <td>
			        	${entrega.id_venda}
			        </td>
			        <td>
			        	${entrega.dataPrevista}
			        </td>
			        <td>
			        	${entrega.dataEntrega}
			        </td>
			        <td>
			        	${classVenda2.buscarPorIdCliente(entrega.id_venda).nome}
			        </td>
			        <td>
			        	${classVenda.buscarPorId(entrega.id_venda).valorVenda}
			        </td>
			        
					<c:if test="${not classEntrega.isVendaEntregue2(entrega.id_venda)}">
						<td>
							<a href="/db-trab/confirmarEntrega?id_venda=${entrega.id_venda}">Confirmar Entrega</a>
						</td>
					</c:if>
					<c:if test="${classEntrega.isVendaEntregue2(entrega.id_venda) and not classAvaliacao.isEntregaAvaliada(entrega)}">
						<td>
							<a href="/db-trab/avaliar?id_entrega=${entrega.id}">Avaliar</a>
						</td>
					</c:if>
					<c:if test="${classEntrega.isVendaEntregue2(entrega.id_venda) and classAvaliacao.isEntregaAvaliada(entrega)}">
						<td>
							Venda já avaliada!
						</td>
					</c:if>
				</tr>
			</c:forEach>		
		</table>
	</c:if>
	
	<br/>
	
	<c:if test="${empty sessionScope.listaEntregas}">
		Nenhuma entrega foi encontrada!
	</c:if>
	
</body>
</html>