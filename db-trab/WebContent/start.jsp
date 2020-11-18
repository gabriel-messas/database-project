<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>MENU</title>
<style>
	{border: 5px dotted lime;}
	h1{
  		font-size: 75px;
  		height: 50px;
	}
	@import url('https://fonts.googleapis.com/css?family=Amatic+SC');;
	
	.description, .link {
	  font-family: 'Amatic SC', cursive;
	  text-align: center;
	}
	
	.description {
		font-size: 35px;
	}
	
	.btn {
	  border: none;
	  display: block;
	  text-align: center;
	  cursor: pointer;
	  text-transform: uppercase;
	  outline: none;
	  overflow: hidden;
	  position: relative;
	  color: #fff;
	  font-weight: 700;
	  font-size: 15px;
	  background-color: #222;
	  padding: 17px 60px;
	  margin: 0 auto;
	  box-shadow: 0 5px 15px rgba(0,0,0,0.20);
	}
	
	.btn span {
  		position: relative; 
 	 	z-index: 1;
	}
	
	.btn:after {
	  content: "";
	  position: absolute;
	  left: 0;
	  top: 0;
	  height: 490%;
	  width: 140%;
	  background: #78c7d2;
	  -webkit-transition: all .5s ease-in-out;
	  transition: all .5s ease-in-out;
	  -webkit-transform: translateX(-98%) translateY(-25%) rotate(45deg);
	  transform: translateX(-98%) translateY(-25%) rotate(45deg);
	}
	
	.btn:hover:after {
	  -webkit-transform: translateX(-9%) translateY(-25%) rotate(45deg);
	  transform: translateX(-9%) translateY(-25%) rotate(45deg);
	}
	
	.link {
	  font-size: 20px;
	  margin-top: 30px;
	}
	
	.link a {
	  color: #000;
	  font-size: 25px; 
	}
	
	.column {
	  float: left;
	  width: 50%;
	}

	/* Clear floats after the columns */
	.row:after {
	  content: "";
	  display: table;
	  clear: both;
	}
</style>
</head>

<body>
	<div class="row" align="center">
		
		<img style="width:30%;height:30%;" src="Loja.png">
		</br><br>
		${username} logged in successfully
		</br><br>
		<span style="color: white; background-color: black; font-size: 60px; height: 50px;"> MENU </span>
		<hr width=225, align="center">
		
		<div class="column">
			<table>
				<tr>
					<th>
						<form action="adicionar-produto.jsp">
							<button type="submit" class="btn"><span>ADICIONAR PRODUTO</span></button>
						</form>
					</th>
				</tr>
		
				<tr><td><br></td></tr>
		
				<tr>
					<td>
						<form action="buscar-produto.jsp">
							<button type="submit" class="btn"><span>BUSCAR PRODUTO</span></button>
						</form>
					</td>
				</tr>
				
				<tr><td><br></td></tr>
		
				<tr>
					<td>
						<form action="checar">
							<button type="submit" class="btn"><span>CHECAR ESTOQUE MÍNIMO</span></button>
						</form>
					</td>
				</tr>
				
				<tr><td><br></td></tr>
		
				<tr>
					<td>
						<form action="buscarClientes">
							<button type="submit" class="btn"><span>CLIENTES</span></button>
						</form>
					</td>
				</tr>
				
				<tr><td><br></td></tr>
		
				<tr>
					<td>
						<form action="buscarEntregas">
							<button type="submit" class="btn"><span>LISTA DE ENTREGAS</span></button>
						</form>
					</td>
				</tr>
				
				<tr><td><br></td></tr>
		
				<tr>
					<td>
						<form action="buscarAvaliacoes">
							<button type="submit" class="btn"><span>LISTA DE AVALIAÇÕES</span></button>
						</form>
					</td>
				</tr>
			</table>
		</div>
			
		<div class="column">
			<table>
				<tr>
					<td>
						<form action="buscarFornecedores">
							<button type="submit" class="btn"><span>FORNECEDORES</span></button>
						</form>
					</td>
				</tr>
				
				<tr><td><br></td></tr>
				
				<tr>
					<td>
						<form action="buscarVendas">
							<button type="submit" class="btn"><span>LISTA DE VENDAS</span></button>
						</form>
					</td>
				</tr>
				
				<tr><td><br></td></tr>
		
				<tr>
					<td>
						<form action="buscarFuncionarios">
							<button type="submit" class="btn"><span>GERENCIAR FUNCIONÁRIOS</span></button>
						</form>
					</td>
				</tr>
				
				<tr><td><br></td></tr>
		
				<tr>
					<td>
						<form action="finalizarVenda">
							<button type="submit" class="btn"><span>FINALIZAR VENDA</span></button>
						</form>
					</td>
				</tr>
				
				<tr><td><br></td></tr>
		
				<tr>
					<td>
						<form action="buscarEntregasAvaliar">
							<button type="submit" class="btn"><span>AVALIAR VENDA</span></button>
						</form>
					</td>
				</tr>
			</table>
		</div>
		
	</div>
	
</body>
</html>