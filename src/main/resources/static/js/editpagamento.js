function disableWarning(){
	if(document.getElementById('warning')){
		document.getElementById('warning').style.display="none";
	}
}

// VALOR DO PAGAMENTO
var valorPagamento = ((document.getElementById('valorPagamento')).value).replace("R$", "").replace(" ", "");
var btnCadastro = document.getElementById('btn_salvar');

function disableWarning(){
	if(document.getElementById('warning')){
		document.getElementById('warning').style.display="none";
	}
}

function alteraCampos(){

	// INICIALIZANDO VARIÁVEIS
	var totalDesconto = 0;
	var newValorPagamento = valorPagamento;

	// MEIO DE PAGAMENTO
	var meioPagamento = (document.getElementById('meioPagamento')).value;

	// PERIODICIDADE DO PAGAMENTO
	var periodoPagamento = (document.getElementById('periodoPagamento')).value

	// DESCONTO NO PAGAMENTO
	var descontoPagamento = (document.getElementById('descontoPagamento')).value;

	// MODALIDADE DO PLANO
	var tipoPlano = (document.getElementById('tipoPlano')).value;

	if(meioPagamento == "ESPECIE" || meioPagamento == "DEBITO" 
		|| meioPagamento == "PIX"){
		totalDesconto += 2;
	}

	if(periodoPagamento == "TRIMESTRAL"){
		totalDesconto += 4;
		newValorPagamento *= 3;
	}
	else if(periodoPagamento == "SEMESTRAL"){
		totalDesconto += 8;
		newValorPagamento *= 6;
	}
	else if(periodoPagamento == "ANUAL"){
		totalDesconto += 12;
		newValorPagamento *= 12;
	}

	if(totalDesconto != 0){
		var valorDesconto = (newValorPagamento/100) * totalDesconto;
		newValorPagamento = newValorPagamento - valorDesconto;
	}

	if(newValorPagamento != valorPagamento){
		document.getElementById('valorPagamento').value = newValorPagamento.toFixed(2);
		document.getElementById('descontoPagamento').value = valorDesconto.toFixed(2);
	}
	else{
		document.getElementById('valorPagamento').value = valorPagamento;
		document.getElementById('descontoPagamento').value = "0.00";
	}

	if(meioPagamento != "" && periodoPagamento != ""){
		console.log("Tchau");
		btnCadastro.disabled=false;
	}
	else{
		console.log("Oi");
		btnCadastro.disabled=true;
	}

}