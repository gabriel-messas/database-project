<%@page import="trab.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Efetuar Alteração</title>
</head>
<body>
	<jsp:useBean id="funcionario" class="trab.AlterarFuncionarioServlet"/>

	<form action="efetuarAlteracaoFuncionario" method="POST">
		Nome: <input type="text" name="nome" value="${funcionario.lastFuncionarioAlterado.nome}"/> <br />
		<br/>
		Idade: <input type="text" name="idade" value="${funcionario.lastFuncionarioAlterado.idade}"/> <br />
		<br/>
		Salário: <input type="text" name="salario" value="${funcionario.lastFuncionarioAlterado.salario}"/> <br />
		<br/>
		Hora de Início: <input type="text" name="horaInicio" value="${funcionario.lastFuncionarioAlterado.horaInicio}"/> <br />
		<br/>
		Hora de Término: <input type="text" name="horaFim" value="${funcionario.lastFuncionarioAlterado.horaFim}"/> <br />
		<br/>
		<input type="hidden" name=id value="${funcionario.lastFuncionarioAlterado.id}"/> <br />
		<input type="submit" value="Alterar" />
	</form>
	
	<form action="funcionarios.jsp">
		<br/>
		<input type="submit" value="Cancelar" />
	</form>
</body>
</html>