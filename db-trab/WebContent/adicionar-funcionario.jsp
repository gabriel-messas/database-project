	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Funcion�rio</title>
</head>
<body>
	<form action="adicionarFuncionario" method="POST">
		
		Nome: <input type="text" name="nome"/> <br />
		<br/>
		Idade: <input type="text" name="idade"/> <br />
		<br/>
		Sal�rio: <input type="text" name="salario"/> <br />
		<br/>
		Hora de In�cio: <input type="text" name="horaInicio"/> <br />
		<br/>
		Hora de T�rmino: <input type="text" name="horaFim"/> <br />
		<br/>
		<input type="submit" value="Adicionar Funcion�rio"/> <br />
	</form>
	
	<form action="start.jsp">
		<br/>
		<input type="submit" value="Voltar ao Menu" />
	</form>
</body>
</html>