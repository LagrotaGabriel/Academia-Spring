package br.com.academia.models.entities;

import br.com.academia.models.enums.FormaPagamento;
import br.com.academia.models.enums.Modalidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;

/** Classe para objetos do tipo Pagamento, onde serão contidos valores e métodos para o mesmo
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0
 * @since 05/03/2022 - 02:19am */

@Entity
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_pagamento", name = "pagamento")
@Table(name = "TB_PAGAMENTO")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pagamento")
    @Column(name = "id_pagamento", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "vl_pagamento", updatable = true, nullable = false, unique = false, length = 10)
    private Double valor;

    @Column(name = "desconto_pagamento", updatable = true, nullable = true, unique = false, length = 10)
    private Double desconto;

    @Column(name = "vcto_pagamento", updatable = true, nullable = false, unique = false, length = 10)
    private String vencimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "mod_pagamento", updatable = true, nullable = false, unique = false)
    private Modalidade modalidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", updatable = true, nullable = false, unique = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    /** Método construtor sem parâmetros da classe Pagamento */
    public Pagamento() {
    }

    /** Método construtor com parâmetros da classe Pagamento
     * @param valor Double - Recebe o valor a pagar
     * @param desconto Double - Recebe o valor de desconto gerado
     * @param vencimento String - Recebe a data de vencimento para pagamento
     * @param modalidade Modalidade - Recebe a modalidade de pagamento (Mensal, Trimestral, Semestral, Anual)
     * @param formaPagamento FormaPagamento - Recebe a foram de pagamento (Espécie, Débito, Crédito, Pix)
     * @param cliente Cliente - Recebe o cliente que realizou o pagamento */
    public Pagamento(Double valor, Double desconto, String vencimento, Modalidade modalidade, FormaPagamento formaPagamento, Cliente cliente) {
        this.valor = valor;
        this.desconto = desconto;
        this.vencimento = vencimento;
        this.modalidade = modalidade;
        this.formaPagamento = formaPagamento;
        this.cliente = cliente;
    }

    /** Método que retorna o id do pagamento
     * @return Integer - Retorna id do pagamento */
    public Long getId() {
        return id;
    }

    /** Método que seta o id do pagamento
     * @param id Integer - Id do pagamento
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** Método que retorna o valor do pagamento
      * @return Double - Retorna valor do pagamento */
    public Double getValor() {
        return valor;
    }

    /** Método que seta o valor do pagamento
     * @param valor Double - Valor do pagamento */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /** Método que retorna o desconto gerado para o pagamento
     * @return Double - Desconto do pagamento */
    public Double getDesconto() {
        return desconto;
    }

    /** Método que seta o valor do desconto do pagamento
     * @param desconto Double - Desconto do pagamento */
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    /** Método que retorna a data de vencimento para o pagamento
     * @return String - Data de vencimento do pagamento */
    public String getVencimento() {
        return vencimento;
    }

    /** Método que seta a data de vencimento do pagamento
     * @param vencimento String - Data de vencimento do pagamento */
    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    /** Método que retorna a modalidade de pagamento utilizada
     * @return Modalidade - Modalidade de pagamento */
    public Modalidade getModalidade() {
        return modalidade;
    }

    /** Método que seta a modalidade de pagamento a ser utilizada
     * @param modalidade Modalidade - Modalidade de pagamento */
    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    /** Método que retorna a forma na qual o pagamento será realizado
     * @return FormaPagamento - Forma de pagamento */
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    /** Método que seta a forma na qual o pagamento será realizado
     * @param formaPagamento FormaPagamento - Forma de pagamento */
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /** Método que retorna o cliente pertinente ao pagamento
     * @return Cliente - Cliente que irá realizar o pagamento */
    public Cliente getCliente() {
        return cliente;
    }

    /** Método que seta o cliente que irá realizar o pagamento
     * @param cliente Cliente - Cliente que irá realizar o pagamento */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
