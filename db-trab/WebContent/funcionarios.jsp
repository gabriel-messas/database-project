<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Funcionários</title>
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

	<form action="adicionar-funcionario.jsp">
		<br/>
		<input type="submit" value="Adicionar Funcionario" />
		<br/>
		<br/>
	</form>
	
	<hr>
	
	<div class="row">
		<div>
			<form action="buscarFuncionarios">
				Digite o nome do funcionario:
				<input type="text" name="nome"/>
				<br/><br/>
				<input type="submit" value="Buscar"/>
				<br/><br/>
				Dica: Busque sem digitar nada para obter a agenda completa de funcionários
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
	
	<c:if test="${not empty requestScope.listaFuncionarios}">
	<br/>
		Funcionarios:
		<br/><br/>
		<table>
			<tr>
				<th> Nome </th>
				<th> Idade </th>
				<th> Salário </th>
				<th> Hora de Início </th>
				<th> Hora de Término </th>
			</tr>
			<c:forEach var="funcionario" items="${requestScope.listaFuncionarios}">
				<tr>
			        <td>
			        	${funcionario.nome}
			        </td>
			        <td>
			        	${funcionario.idade}
			        </td>
			        <td>
			        	${funcionario.salario}
			        </td>
			        <td>
			        	${funcionario.horaInicio}
			        </td>
			        <td>
			        	${funcionario.horaFim}
			        </td>
			        <td>
						<a href="/db-trab/alterarFuncionario?id=${funcionario.getId()}">Alterar</a>
					</td>
					<td>
						<a href="/db-trab/excluirFuncionario?id=${funcionario.getId()}">Demitir</a>
					</td>
					<td>
						<a href="/db-trab/selecionarFuncionario?id=${funcionario.getId()}">Selecionar como Vendedor</a>
					</td>
				</tr>
			</c:forEach>		
		</table>
	</c:if>
	
	<br/>
	
	<c:if test="${empty requestScope.listaFuncionarios}">
		Nenhum funcionário foi encontrado!
	</c:if>
	
</body>
</html>