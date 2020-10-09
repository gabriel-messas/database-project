<%@page import="trab.Produto"%>
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
	<jsp:useBean id="contato" class="trab.AlterarContatoServlet"/>

	<form action="efetuarAlteracaoContato" method="POST">
		Nome: <input type="text" name="nome" value="${contato.lastContatoAlterado.nome}"/> <br />
		Apelido: <input type="text" name="apelido" value="${contato.lastContatoAlterado.apelido}"/> <br />
		Empresa: <input type="text" name="empresa" value="${contato.lastContatoAlterado.empresa}"/> <br />
		Endereço: <input type="text" name="endereco" value="${contato.lastContatoAlterado.endereco}"/> <br />
		Bairro: <input type="text" name="bairro" value="${contato.lastContatoAlterado.bairro}"/> <br />
		Cidade: <input type="text" name="cidade" value="${contato.lastContatoAlterado.cidade}"/> <br />
		Telefone 1: <input type="text" name="telefone1" value="${contato.lastContatoAlterado.telefone1}"/> <br />
		Telefone 2: <input type="text" name="telefone2" value="${contato.lastContatoAlterado.telefone2}"/> <br />
		Telefone 3: <input type="text" name="telefone3" value="${contato.lastContatoAlterado.telefone3}"/> <br />
		Observação: <input type="text" name="observacao" value="${contato.lastContatoAlterado.observacao}"/> <br />
		Cliente: <input type="text" name="cliente" disabled value=<c:if test="${contato.lastContatoAlterado.cliente==1}">"CLIENTE"</c:if><c:if test="${contato.lastContatoAlterado.cliente==0}">"FORNECEDOR"</c:if>/>
		<input type="hidden" name=id value="${contato.lastContatoAlterado.id}"/> <br />
		<input type="submit" value="Alterar" />
	</form>
	
	<form action="agenda.jsp">
		<br/>
		<input type="submit" value="Cancelar" />
	</form>
</body>
</html>