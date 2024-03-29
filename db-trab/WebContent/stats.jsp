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
		</div>
		
		<form action="start.jsp">
			<br/><br/>
			<input type="submit" value="Retornar ao Menu" />
			<br/><br/>
		</form>
		
		<form action="stats2.jsp">
			<input type="submit" value="Pr�xima" />
			<br/><br/>
		</form>
	</div>
	
	<hr>
	
	<br/>
	
	<jsp:useBean id="venda" class="trab.VendaDAO"/>
	
	<script type="text/javascript">
		document.addEventListener('DOMContentLoaded', (event) => {
		    const labels = [
				'January',
				'February',
				'March',
				'April',
				'May',
				'June',
				'July',
				'August',
				'September',
				'October',
				'November',
				'December',
			];
		    const data = {
				labels: labels,
				datasets: [{
				  label: 'Quantidade de Vendas por M�s',
				  backgroundColor: 'rgb(255, 99, 132)',
				  borderColor: 'rgb(255, 99, 132)',
				  data: [${venda.getAmountByMonth(01)}, ${venda.getAmountByMonth(02)}, ${venda.getAmountByMonth(03)}, ${venda.getAmountByMonth(04)}, 
					     ${venda.getAmountByMonth(05)}, ${venda.getAmountByMonth(06)}, ${venda.getAmountByMonth(07)}, ${venda.getAmountByMonth(08)},
					     ${venda.getAmountByMonth(09)}, ${venda.getAmountByMonth(10)}, ${venda.getAmountByMonth(11)}, ${venda.getAmountByMonth(12)}],
				}]
			};
		    var delayed;
		    const config = {
				type: 'line',
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
					      delay = context.dataIndex * 30 + context.datasetIndex * 10;
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