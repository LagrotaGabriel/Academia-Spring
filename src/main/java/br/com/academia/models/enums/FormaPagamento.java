package br.com.academia.models.enums;

/** Classe para ENUMS do tipo FormaPagamento, onde serão contidos valores e métodos para o mesmo
 * @since 13/03/2022 - 17:48pm
 * @author gahcruz - Gabriel Lagrota
 * @version 1.0 */
public enum FormaPagamento {

    ESPECIE(1, "Espécie"),DEBITO(2, "Cartão de débito"),
    CREDITO(3, "Cartão de crédito"), PIX(4, "Pix");

    private Integer code;
    private String descricao;

    /** Método construtor com parâmetros da classe FormaPagamento
     * @param code Integer - Recebe o código de identificação da constante
     * @param descricao String - Recebe a descrição da constante */
    private FormaPagamento(Integer code, String descricao){
        this.code = code;
        this.descricao = descricao;
    }

    /** Método que retorna o código da constante
     * @return Integer - Código de identificação da constante */
    public Integer getCode(){
        return code;
    }

    /** Método que retorna a descrição da constante
     * @return String - Descrição da constante */
    public String getDescricao(){
        return descricao;
    }

}
