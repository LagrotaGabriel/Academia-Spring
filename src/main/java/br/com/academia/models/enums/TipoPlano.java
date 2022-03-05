package br.com.academia.models.enums;

public enum TipoPlano {

    BASICO(1, "Básico", 70.00),
    INTERMEDIARIO(2, "Intermediário", 100.00),
    AVANCADO(3, "Avançado", 140.00);

    private Integer code;
    private String descricao;
    private Double preco;

    TipoPlano(Integer code, String descricao, Double preco) {
        this.code = code;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }
}
