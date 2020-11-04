<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Funcionário Escolhido</title>
</head>
<body>
<jsp:useBean id="funcionario" class="trab.SelecionarFuncionarioServlet"/>

O funcionário ${funcionario.funcionario.nome} foi selecionado com sucesso!
	
	<form action="finalizarVenda">
		<br/>
		<input type="submit" value="Finalizar Venda" />
	</form>
</body>
</html>