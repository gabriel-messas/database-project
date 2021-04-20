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

	<jsp:useBean id="avaliacao" class="trab.AvaliacaoDAO"/>
	
	<div style="margin: auto; width: 70%; text-align: center;">
		<div>
			<canvas id="myChart"></canvas>
			<c:if test="${avaliacao.statsNotaBaixaPorEntregaLenta() == 0}">
				<p>Não há avaliações com notas abaixo de 5</p>
			</c:if>
		</div>
		
		<form action="start.jsp">
			<br/><br/>
			<input type="submit" value="Retornar ao Menu" />
			<br/><br/>
		</form>
		
		<form action="stats4.jsp">
			<input type="submit" value="Anterior" />
			<br/><br/>
		</form>
		
		<form action="stats5.jsp">
<!-- 		<input type="submit" value="Próxima" /> -->
			<br/><br/>
		</form>
	</div>
	
	<hr>
	
	<br/>
	
	<script type="text/javascript">
		document.addEventListener('DOMContentLoaded', (event) => {
			const labels = ['Notas abaixo de 5 com entregas demoradas', 'Notas abaixo de 5 com entregas normais'];
			
			const data = {
				labels: labels,
				datasets: [
					{
						label: 'Notas abaixo de 5 com entregas demoradas',
					    data: [ ${avaliacao.statsNotaBaixaPorEntregaLenta()}
					    ],
					    backgroundColor: 'rgb(255, 99, 132)',
					},
					{
						label: 'Notas abaixo de 5 com entregas normais',
					    data: [100 - ${avaliacao.statsNotaBaixaPorEntregaLenta()}
					    ],
					    backgroundColor: 'rgb(132, 255, 99)',
					},
				]
			};
			
			var delayed;
			const config = {
				type: 'pie',
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
			
			<c:if test="${avaliacao.statsNotaBaixaPorEntregaLenta() != 0}">
				const myChart = new Chart(
				  document.getElementById('myChart'),
				  config
				);
			</c:if>
	    	
		});
		
	</script>
	
</body>
</html>