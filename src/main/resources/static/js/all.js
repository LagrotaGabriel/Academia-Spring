

function estadoBotao(){

	var inputCpf = document.getElementById('pesquisaInputCpf').value;
	var inputRg = document.getElementById('pesquisaInputRg').value;
	console.log(inputCpf);
	console.log(inputRg);

	if(inputCpf == "" || inputRg == ""){
		console.log("Acessado");
		document.getElementById('pesquisaBtn').disabled=true;
	}
	else{
		document.getElementById('pesquisaBtn').disabled=false;
	}
}

function disableWarning(){
	if(document.getElementById('warning')){
		document.getElementById('warning').style.display="none";
	}
}