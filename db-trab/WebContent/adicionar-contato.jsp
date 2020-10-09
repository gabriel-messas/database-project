	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Contato</title>
</head>
<body>
	<form action="adicionarContato" method="POST">
		<p>Tipo do Contato</p>
	    <input type="radio" id="cliente" name="cliente" value="1">
	    <label for="cliente">Cliente</label><br>
	    <input type="radio" id="fornecedor" name="cliente" value="0">
	    <label for="fornecedor">Fornecedor</label><br/><br/>
		Nome: <input type="text" name="nome"/> <br />
		<br/>
		Apelido: <input type="text" name="apelido"/> <br />
		<br/>
		Empresa: <input type="text" name="empresa"/> <br />
		<br/>
		Endereço: <input type="text" name="endereco"/> <br />
		<br/>
		Bairro: <input type="text" name="bairro"/> <br />
		<br/>
		Cidade: <input type="text" name="cidade"/> <br />
		<br/>
		Telefone 1: <input type="text" name="telefone1"/> <br />
		<br/>
		Telefone 2: <input type="text" name="telefone2"/> <br />
		<br/>
		Telefone 3: <input type="text" name="telefone3"/> <br />
		<br/>
		Observação: <input type="text" name="observacao"/> <br />
		<br/>
		<input type="submit" value="Adicionar Contato"/> <br />
	</form>
	
	<form action="start.jsp">
		<br/>
		<input type="submit" value="Voltar ao Menu" />
	</form>
</body>
</html>