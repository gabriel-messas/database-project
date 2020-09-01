	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Produto</title>

<script type="text/javascript" src="script.js" ></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<body>
	<form action="adicionar" method="POST" name="adicionar">
		Nome: <input type="text" name="nome"/> <br />
		<br/>
		Preço: <input type="text" name="preco"/> <br />
		<br/>
		Quantidade: <input type="text" name="quantidade"/> <br />
		<br/>
		<input type="button" value="Cadastrar" onclick="validaInput()"/> <br />
	</form>
	
	<form action="start.jsp">
		<br/>
		<input type="submit" value="Voltar" />
	</form>
</body>
</html>