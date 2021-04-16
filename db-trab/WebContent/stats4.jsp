<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Estat�sticas</title>
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
			<p>aaaaaa</p>
		</div>
		
		<form action="start.jsp">
			<br/><br/>
			<input type="submit" value="Retornar ao Menu" />
			<br/><br/>
		</form>
		
		<form action="stats3.jsp">
			<input type="submit" value="Anterior" />
			<br/><br/>
		</form>
		
		<form action="stats5.jsp">
			<input type="submit" value="Pr�xima" />
			<br/><br/>
		</form>
	</div>
	
	<hr>
	
	<br/>
	
	<jsp:useBean id="produto" class="trab.ProdVendaDAO"/>
	
	<script type="text/javascript">
		document.addEventListener('DOMContentLoaded', (event) => {
			const labels = ['< 18', '18 a 30', '31 a 50', '> 50'];
			
			const data = {
				labels: labels,
				datasets: [
					{
						label: 'Mais Vendido',
					    data: [${produto.statsMaisVentidoFaixaEtaria(0, 18)}, ${produto.statsMaisVentidoFaixaEtaria(18, 30)}, 
					    	   ${produto.statsMaisVentidoFaixaEtaria(31, 50)}, ${produto.statsMaisVentidoFaixaEtaria(50, 120)}
					    ],
					    backgroundColor: 'rgb(255, 99, 132)',
					},
					{
						label: 'Menos Vendido',
					    data: [${produto.statsMenosVentidoFaixaEtaria(0, 18)}, ${produto.statsMenosVentidoFaixaEtaria(18, 30)}, 
					    	   ${produto.statsMenosVentidoFaixaEtaria(31, 50)}, ${produto.statsMenosVentidoFaixaEtaria(50, 120)}
					    ],
					    backgroundColor: 'rgb(132, 255, 99)',
					},
				]
			};
			
			var delayed;
			const config = {
				type: 'bar',
				data,
				options: { 
					responsive: true,
					scales: {
				      x: {
				        stacked: true,
				      },
				      y: {
				        stacked: true
				      }
				    },
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
			
			const myChart = new Chart(
			  document.getElementById('myChart'),
			  config
			);
		});
		
	</script>
	
</body>
</html>