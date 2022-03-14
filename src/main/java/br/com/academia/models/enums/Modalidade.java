package br.com.academia.models.enums;

/** Classe para ENUMS do tipo Modalidade, onde serão contidos valores e métodos para o mesmo
 * @since 13/03/2022 - 17:53pm
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0 */
public enum Modalidade {

    MENSAL(1, "Mensal"), TRIMESTRAL(2, "Trimestral"),
    SEMESTRAL(3, "Semestral"), ANUAL(4, "Anual");

    private Integer code;
    private String descricao;

    /** Método construtor com parâmetros da classe Modalidade
     * @param code - Integer - Recebe o código de identificação da constante
     * @param descricao - String - Receb a descrição da constante */
    Modalidade(Integer code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    /** Método usado para retornar o código de identificação da constante
     * @return - Integer - Código de identificação da constante */
    public Integer getCode() {
        return code;
    }

    /** Método usado para retornar a descrição da constante
     * @return - String - Descrição da constante */
    public String getDescricao() {
        return descricao;
    }
}
