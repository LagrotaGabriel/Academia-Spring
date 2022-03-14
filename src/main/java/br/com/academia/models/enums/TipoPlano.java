package br.com.academia.models.enums;

/** Classe para ENUMS do tipo TipoPlano, onde serão contidos os valores e métodos para o mesmo
 * @since 13/03/2022 - 17:57pm
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0 */
public enum TipoPlano {

    BASICO(1, "Básico", 70.00),
    INTERMEDIARIO(2, "Intermediário", 100.00),
    AVANCADO(3, "Avançado", 140.00);

    private Integer code;
    private String descricao;
    private Double preco;

    /** Método construtor com parâmetros da classe ENUM TipoPlano
     * @param code - Integer - Recebe o código de identificação da constante
     * @param descricao String - Recebe a descrição da constante
     * @param preco Double - Recebe o valor monetário da constante */
    TipoPlano(Integer code, String descricao, Double preco) {
        this.code = code;
        this.descricao = descricao;
        this.preco = preco;
    }

    /** Método que retorna o código de identificação da constante
     * @return - Integer - Código de identificação da constante */
    public Integer getCode() {
        return code;
    }

    /** Método que retorna a descrição da constante
     * @return - String - Descrição da constante */
    public String getDescricao() {
        return descricao;
    }

    /** Método que retorna o preço da constante
     * @return Double - Valor da constante
     */
    public Double getPreco() {
        return preco;
    }
}
