	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Produto</title>

<script type="text/javascript" src="script.js" ></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<body>

	<c:if test="${empty sessionScope.fornecedor}">
		É necessário antes selecionar um fornecedor!
		
		<form action="fornecedores.jsp">
			<br/>
			<input type="submit" value="Listar Fornecedores" />
		</form>
	</c:if>
	<c:if test="${not empty sessionScope.fornecedor}">
		<form action="adicionar" method="POST" name="adicionar">
			Nome: <input type="text" name="nome"/> <br />
			<br/>
			Preço de Compra 1: <input type="text" name="precoCompra1"/> <br />
			<br/>
			Preço de Compra 2: <input type="text" name="precoCompra2"/> <br />
			<br/>
			Preço de Venda: <input type="text" name="precoVenda"/> <br />
			<br/>
			Quantidade: <input type="text" name="quantidade"/> <br />
			<br/>
			Fornecedor: <input type="text" name="fornecedor" disabled value="${sessionScope.fornecedor.nome}"/> <br />
			<br/>
			<input type="button" value="Cadastrar Produto" onclick="validaInput()"/> <br />
		</form>
		
		<form action="start.jsp">
			<br/>
			<input type="submit" value="Voltar ao Menu" />
		</form>
	</c:if>
</body>
</html>