<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Venda Exclu�da</title>
</head>
<body>
	
	A venda de ID <c:out value="${sessionScope.lastVendaRemoved.id}" /> foi exclu�da com sucesso!
	
	<form action="buscarVendas">
		<br/>
		<input type="submit" value="Voltar �s Vendas" />
	</form>
</body>
</html>