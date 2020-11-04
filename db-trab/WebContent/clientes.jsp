<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clientes</title>
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

	<form action="adicionar-contato.jsp">
		<br/>
		<input type="submit" value="Adicionar Cliente" />
		<br/>
		<br/>
	</form>
	
	<hr>
	
	<div class="row">
		<div class="column border-right">
			<form action="buscarClientes">
				Digite o nome, apelido ou empresa do cliente:
				<input type="text" name="nome"/>
				<br/><br/>
				<input type="submit" value="Buscar"/>
				<br/><br/>
				Dica: Busque sem digitar nada para obter a agenda completa de clientes
			</form>
			
		</div>
		
		<div class="column" style="padding-left: 15px;">
			<form action="buscarClientesEndereco">
				Digite o endereço ou bairro do cliente:
				<input type="text" name="nome"/>
				<br/><br/>
				<input type="submit" value="Buscar"/>
				<br/><br/>
				Dica: Busque sem digitar nada para obter a agenda completa de clientes
			</form>
		</div>
	</div>
	
	<hr>

	<form action="start.jsp">
		<br/>
		<input type="submit" value="Retornar ao Menu" />
		<br/><br/>
	</form>
	
	<hr>
	
	<c:if test="${not empty requestScope.listaClientes}">
	<br/>
		Clientes:
		<br/><br/>
		<table>
			<tr>
				<th> Tipo </th>
				<th> Nome </th>
				<th> Apelido </th>
				<th> Empresa </th>
				<th> Endereço </th>
				<th> Bairro </th>
				<th> Cidade </th>
				<th> Telefone 1 </th>
				<th> Telefone 2 </th>
				<th> Telefone 3 </th>
				<th> Observação </th>
			</tr>
			<c:forEach var="contato" items="${requestScope.listaClientes}">
				<tr>
					<td>
			        	<c:if test="${contato.cliente==1}">
			        	CLIENTE
			        	</c:if>
			        	<c:if test="${contato.cliente==0}">
			        	FORNECEDOR
			        	</c:if>
			        </td>
			        <td>
			        	${contato.nome}
			        </td>
			        <td>
			        	${contato.apelido}
			        </td>
			        <td>
			        	${contato.empresa}
			        </td>
			        <td>
			        	${contato.endereco}
			        </td>
			        <td>
			        	${contato.bairro}
			        </td>
			        <td>
			        	${contato.cidade}
			        </td>
			        <td>
			        	${contato.telefone1}
			        </td>
			        <td>
			        	${contato.telefone2}
			        </td>
			        <td>
			        	${contato.telefone3}
			        </td>
			        <td>
			        	${contato.observacao}
			        </td>
			        <td>
						<a href="/db-trab/alterarContato?id=${contato.getId()}">Alterar</a>
					</td>
					<td>
						<a href="/db-trab/excluirContato?id=${contato.getId()}">Excluir</a>
					</td>
					<td>
						<c:if test="${contato.cliente==1}">
							<a href="/db-trab/selecionarCliente?id=${contato.getId()}">Selecionar como Cliente</a>
						</c:if>
						<c:if test="${contato.cliente==0}">
							<a href="/db-trab/selecionarFornecedor?id=${contato.getId()}">Selecionar como Fornecedor</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>		
		</table>
	</c:if>
	
	<br/>
	
	<c:if test="${empty requestScope.listaClientes}">
		Nenhum cliente foi encontrado!
	</c:if>
	
</body>
</html>