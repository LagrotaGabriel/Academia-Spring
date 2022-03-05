package br.com.academia.models.enums;

public enum Modalidade {

    MENSAL(1, "Mensal"), TRIMESTRAL(2, "Trimestral"),
    SEMESTRAL(3, "Semestral"), ANUAL(4, "Anual");

    private Integer code;
    private String descricao;

    Modalidade(Integer code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }
}
