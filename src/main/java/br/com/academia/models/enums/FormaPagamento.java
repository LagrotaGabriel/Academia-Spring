package br.com.academia.models.enums;

public enum FormaPagamento {

    ESPECIE(1, "Espécie"),DEBITO(2, "Cartão de débito"),
    CREDITO(3, "Cartão de crédito"), PIX(4, "Pix");

    private Integer code;
    private String descricao;

    private FormaPagamento(Integer code, String descricao){
        this.code = code;
        this.descricao = descricao;
    }

    public Integer getCode(){
        return code;
    }

    public String getDescricao(){
        return descricao;
    }

}
