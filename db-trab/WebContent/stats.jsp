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

	<div class="row">
		<div class="column">
			<canvas id="myChart"></canvas>
		</div>
		<div class="column">
			<canvas id="myChart2"></canvas>
		</div>
	</div>

	<form action="start.jsp">
		<br/><br/>
		<input type="submit" value="Retornar ao Menu" />
		<br/><br/>
	</form>
	
	<form action="stats2.jsp">
		<input type="submit" value="Próxima" />
		<br/><br/>
	</form>
	
	<hr>
	
	<br/>
	
	<jsp:useBean id="venda" class="trab.VendaDAO"/>
	<jsp:useBean id="avaliacao" class="trab.AvaliacaoDAO"/>
	
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
				  label: 'Quantidade de Vendas por Mês',
				  backgroundColor: 'rgb(255, 99, 132)',
				  borderColor: 'rgb(255, 99, 132)',
				  data: [${venda.getAmountByMonth(01)}, ${venda.getAmountByMonth(02)}, ${venda.getAmountByMonth(03)}, ${venda.getAmountByMonth(04)}, 
					     ${venda.getAmountByMonth(05)}, ${venda.getAmountByMonth(06)}, ${venda.getAmountByMonth(07)}, ${venda.getAmountByMonth(08)},
					     ${venda.getAmountByMonth(09)}, ${venda.getAmountByMonth(10)}, ${venda.getAmountByMonth(11)}, ${venda.getAmountByMonth(12)}],
				}]
			};
		    const config = {
				type: 'line',
				data,
				options: {}
			};
			
			const myChart = new Chart(
			  document.getElementById('myChart'),
			  config
			);
			
			const labels2 = [
				'1',
				'2',
				'3',
				'4',
				'5',
			];
		    const data2 = {
				labels: labels2,
				datasets: [{
				  label: 'Quantidade de Avaliações por Nota',
				  backgroundColor: 'rgb(255, 99, 132)',
				  borderColor: 'rgb(255, 99, 132)',
				  data: [${avaliacao.buscarPorNota(1)}, ${avaliacao.buscarPorNota(2)}, ${avaliacao.buscarPorNota(3)}, ${avaliacao.buscarPorNota(4)}, 
					     ${avaliacao.buscarPorNota(5)}, ${avaliacao.buscarPorNota(6)}, ${avaliacao.buscarPorNota(7)}, ${avaliacao.buscarPorNota(8)},
					     ${avaliacao.buscarPorNota(9)}, ${avaliacao.buscarPorNota(10)}],
				}]
			};
		    const configg = {
				type: 'line',
				data2,
				options: {}
			};
			
			const myChart2 = new Chart(
			  document.getElementById('myChart2'),
			  configg
			);
		});
		
	</script>
	
</body>
</html>