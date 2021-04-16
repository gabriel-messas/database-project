<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Estatísticas</title>
	<style>
		table, th, td { border: 1px solid black; }
		.column {
	    float: left;
	    width: 45%;
	    }
	    .row:after {
	    content: "";
	    display: table;
	    clear: both;
	    }
	    .border-right {
	    border-right: 1px solid black;
		}
		.border-left {
		border-left:  1px solid black;
		}
	</style>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	
</head>
<body>

	<div style="margin: auto; width: 70%; text-align: center;">
		<div>
			<canvas id="myChart"></canvas>
			<p>No eixo Y, o fornecedor mais barato por produto (1, 2 ou 3 (caso o preço de ambos seja igual))</p>
		</div>
		
		<form action="start.jsp">
			<br/><br/>
			<input type="submit" value="Retornar ao Menu" />
			<br/><br/>
		</form>
		
		<form action="stats2.jsp">
			<input type="submit" value="Anterior" />
			<br/><br/>
		</form>
		
		<form action="stats4.jsp">
			<input type="submit" value="Próxima" />
			<br/><br/>
		</form>
	</div>
	
	<hr>
	
	<br/>
	
	<jsp:useBean id="produto" class="trab.ProdutoDAO"/>
	
	<script type="text/javascript">
		document.addEventListener('DOMContentLoaded', (event) => {
			${produto.statsPrecoFornecedor()}
			
			var delayed;
			const config = {
				type: 'bar',
				data,
				options: { 
					responsive: true,
					animation: {
					  onComplete: () => {
					    delayed = true;
					  },
					  delay: (context) => {
					    let delay = 0;
					    if (context.type === 'data' && context.mode === 'default' && !delayed) {
					      delay = context.dataIndex * 60 + context.datasetIndex * 20;
					    }
					    return delay;
					  },
					},
				}
			};
			
			const myChart2 = new Chart(
			  document.getElementById('myChart'),
			  config
			);
		});
		
	</script>
	
</body>
</html>