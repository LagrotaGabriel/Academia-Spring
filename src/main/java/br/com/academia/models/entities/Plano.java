package br.com.academia.models.entities;

import br.com.academia.models.enums.TipoPlano;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/** Classe para objetos do tipo Plano, onde serão contidos valores e métodos para o mesmo
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0
 * @since 05/03/2022 - 01:54am */

@Entity
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_plano", name = "plano")
@Table(name = "TB_PLANO")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "plano")
    @Column(name = "id_plano", unique = true, nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano", unique = false, nullable = false, updatable = true)
    private TipoPlano tipoPlano;

    @Column(name = "data_assinatura", unique = false, nullable = false, updatable = false)
    private String dataAssinatura;

    @Column(name = "idCliente", unique = false, nullable = true, updatable = true)
    private Integer idCliente;

    @JsonIgnore
    @OneToOne(mappedBy = "plano")
    private Cliente cliente;

    /** Método construtor sem parâmetros da classe Plano */
    public Plano() {
    }


    /** Método construtor com parâmetros da classe Plano
     * @param tipoPlano TipoPlano - Recebe o tipo do plano
     * @param dataAssinatura String - Recebe a data de assinatura do plano
     * @param cliente Cliente - Recebe o cliente contratante do plano */
    public Plano(TipoPlano tipoPlano, String dataAssinatura, Integer idCliente,Cliente cliente) {
        this.tipoPlano = tipoPlano;
        this.dataAssinatura = dataAssinatura;
        this.cliente = cliente;
        this.idCliente = idCliente;
    }

    /** Método que retorna o id do Plano
     * @return Long - Número de identificação do plano */
    public Long getId() {
        return id;
    }

    /** Método usado para setar o id do plano
     * @param id Long - Seta o número de identificação do plano */
    public void setId(Long id) {
        this.id = id;
    }

    /** Método que retorna o Enum com o tipo de plano e seus atributos
     * @return TipoPlano - Método que retorna o tipo de plano do plano */
    public TipoPlano getTipoPlano() {
        return tipoPlano;
    }

    /** Método usado para setar o tipo do plano
     * @param tipoPlano TipoPlano - Seta o tipo de plano do plano */
    public void setTipoPlano(TipoPlano tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    /** Método que retorna a data de assinatura do plano
     * @return String - Data que o plano foi assinado */
    public String getDataAssinatura() {
        return dataAssinatura;
    }

    /** Método usado para setar a data de assinatura do plano
     * @param dataAssinatura String - Data que o plano foi assinado */
    public void setDataAssinatura(String dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    /** Método que retorna o id do cliente que contém o plano
     * @return Integer - Id do cliente que contém o plano */
    public Integer getIdCliente() {
        return idCliente;
    }

    /** Método usado para setar o id do cliente que contém o plano
     * @param idCliente Integer - ID Do cliente que contém o plano */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /** Método que retorna o cliente que possui o plano
     * @return Cliente - Cliente que possui o plano */
    public Cliente getCliente() {
        return cliente;
    }

    /** Método que seta o cliente que possui o plano
     * @param cliente Cliente - Cliente que possui o plano */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
