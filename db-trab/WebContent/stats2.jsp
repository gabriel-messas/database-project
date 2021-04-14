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
	</div>

	<form action="start.jsp">
		<br/><br/>
		<input type="submit" value="Retornar ao Menu" />
		<br/><br/>
	</form>
	
	<form action="stats.jsp">
		<input type="submit" value="Anterior" />
		<br/><br/>
	</form>
	
	<form action="stats3.jsp">
		<input type="submit" value="Próxima" />
		<br/><br/>
	</form>
	
	<hr>
	
	<br/>
	
	<jsp:useBean id="venda" class="trab.VendaDAO"/>
	<jsp:useBean id="avaliacao" class="trab.AvaliacaoDAO"/>
	
	<script type="text/javascript">
		document.addEventListener('DOMContentLoaded', (event) => {			
			const labels2 = [
				'1',
				'2',
				'3',
				'4',
				'5',
				'6',
				'7',
				'8',
				'9',
				'10',
			];
		    const data = {
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
				data,
				options: {}
			};
			
			const myChart2 = new Chart(
			  document.getElementById('myChart'),
			  configg
			);
		});
		
	</script>
	
</body>
</html>