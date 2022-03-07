package br.com.academia.validations;


public class PagamentoValidation {

    public Boolean validaValor(Double valor){

        String valorString = valor.toString();
        if(valorString.matches("[A-Z a-z]*")){
            return false;
        }
        else{
            return true;
        }
    }



}
