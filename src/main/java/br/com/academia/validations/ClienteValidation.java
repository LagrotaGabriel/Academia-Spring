package br.com.academia.validations;

import br.com.academia.models.entities.Cliente;

public class ClienteValidation {

    // Valida nome
    public Boolean validaNome(String nome){

        // SE O NOME FOR NULO OU VAZIO
        if(nome == null || nome.equals("")){
            return false;
        }
        // SE O NOME NÃO FOR NULO OU VAZIO
        else{
            // SE O NOME TIVER NÚMEROS
            if(nome.matches("[0-9]*")){
                return false;
            }
            // SE O NOME NÃO TIVER NÚMEROS
            else{
                // SE O NOME TIVER MAIS DE 30 CARACTERES
                if(nome.length() > 30){
                    return false;
                }
                // SE O NOME NÃO TIVER MAIS DE 30 CARACTERES
                else{
                    return true;
                }
            }
        }
    }

    // Valida sobrenome
    public Boolean validaSobrenome(String sobreNome){
        // SE O SOBRENOME FOR NULO OU VAZIO
        if(sobreNome == null || sobreNome.equals("")){
            return false;
        }
        // SE O SOBRENOME NÃO FOR NULO OU VAZIO
        else{
            // SE O SOBRENOME TIVER NÚMEROS
            if(sobreNome.matches("[0-9]*")){
                return false;
            }
            // SE O SOBRENOME NÃO TIVER NÚMEROS
            else{
                // SE O SOBRENOME TIVER MAIS DE 30 CARACTERES
                if(sobreNome.length() > 30){
                    return false;
                }
                // SE O SOBRENOME NÃO TIVER MAIS DE 30 CARACTERES
                else{
                    return true;
                }
            }
        }
    }

    // Valida rg
    public Boolean validaRg(String rg){

        // SE O RG TIVER MAIS DO QUE 9 DÍGITOS
        if(rg.length() > 9){
            return false;
        }
        // SE O RG TIVER MENOS DE 10 DÍGITOS
        else{
            // SE O RG POSSUIR LETRAS
            if(rg.matches("[A-Z a-z]*")){
                return false;
            }
            // SE O RG NÃO POSSUIR LETRAS
            else{
                return true;
            }
        }
    }

    // Valida cpf
    public Boolean validaCpf(String cpf){

        // SE O cpf TIVER MAIS DO QUE 11 DÍGITOS
        if(cpf.length() > 11){
            return false;
        }
        // SE O cpf TIVER MENOS DE 12 DÍGITOS
        else{
            // SE O cpf POSSUIR LETRAS
            if(cpf.matches("[A-Z a-z]*")){
                return false;
            }
            // SE O cpf NÃO POSSUIR LETRAS
            else{
                return true;
            }
        }
    }

    // Valida data de nascimento
    public Boolean validaDataNascimento(String dataNascimento){

        if(dataNascimento.equals("") || dataNascimento == null){
            return false;
        }
        else{
            if(dataNascimento.length() != 10){
                return false;
            }
            else{
                return true;
            }
        }
    }

    // Valida tudo
    public Boolean validaCliente(Cliente cliente){
        return
                validaNome(cliente.getNome())
                && validaSobrenome(cliente.getSobrenome())
                && validaRg(cliente.getRg())
                && validaCpf(cliente.getCpf());
    }

}
