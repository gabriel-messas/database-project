
function validaInput() {
	if(document.adicionar.nome.value == ""){
		swal("Dados incompletos", "Insira o nome do produto!", "error");
		return false;
	}
	else if(document.adicionar.precoVenda.value == ""){
		swal("Dados incompletos", "Insira o valor do produto!", "error");
		return false;
	}
	else if(document.adicionar.quantidade.value == ""){
		swal("Dados incompletos", "Insira a quantidade do produto!", "error");
		return false;
	}
	else{
		document.adicionar.submit();
	}
}