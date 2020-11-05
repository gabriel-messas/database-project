<%@page import="trab.Produto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Efetuar Avaliação</title>
</head>
<body>

	<form action="efetuarAvaliacao">
		Nota: <input type="text" name="nota" pattern="10|[0-9]"/> <br />
		Observação: <input type="text" name="observacao"/> <br />
		<input type="hidden" name=id_entrega value="${requestScope.id_entrega}"/> <br />
		<input type="submit" value="Avaliar" />
	</form>
	
	<form action="start.jsp">
		<br/>
		<input type="submit" value="Cancelar" />
	</form>
</body>
</html>