package br.com.academia.models.entities;

import javax.persistence.*;
import java.util.List;

/** Classe para objetos do tipo Cliente, onde serão contidos valores e métodos para o mesmo
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0
 * @since 05/03/2022 - 00:57am*/

@Entity
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_cliente", name = "cliente")
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cliente")
    @Column(name = "id_cliente", updatable = false, unique = true)
    private Long id;

    @Column(name = "nm_cliente", nullable = false, unique = false, length = 30, updatable = false)
    private String nome;

    @Column(name = "sbnm_cliente", nullable = false, unique = false, length = 30, updatable = true)
    private String sobrenome;

    @Column(name = "rg_cliente", nullable = false, unique = true, length = 12, updatable = false)
    private String rg;

    @Column(name = "cpf_cliente", nullable = false, unique = true, length = 14, updatable = false)
    private String cpf;

    @Column(name = "dt_nascimento_cliente", nullable = false, unique = false, length = 10, updatable = false)
    private String dataNascimento;

    @Column(name = "dt_cadastro_cliente", nullable = false, unique = false, length = 10, updatable = false)
    private String dataCadastro;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Plano plano;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pagamento> pagamentos;

    /** Método construtor sem parâmetros da classe Cliente */
    public Cliente() {
    }

    /** Método construtor com parâmetros da classe Cliente
     * @param nome String - Recebe o nome do cliente
     * @param sobrenome String - Recebe o sobrenome do cliente
     * @param rg String - Recebe o RG (Registro geral) do cliente
     * @param cpf String - Recebe o CPF (Cadastro de pessoa física) do cliente
     * @param dataNascimento String - Recebe a data de nascimento do cliente
     * @param plano Plano - Recebe o objeto plano do cliente, contendo seus atributos
     * @param dataCadastro String - Recebe a data de cadastro do cliente */
    public Cliente(String nome, String sobrenome, String rg, String cpf, String dataNascimento, Plano plano, String dataCadastro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.plano = plano;
        this.pagamentos = pagamentos;
        this.dataCadastro = dataCadastro;
    }

    /** Método que retorna o id do cliente
     * @return Long - Número de identificação do cliente */
    public Long getId() {
        return id;
    }

    /** Método usado para setar o id do cliente
     * @param id Long - Seta o número de identificação do cliente */
    public void setId(Long id) {
        this.id = id;
    }

    /** Método que retorna o nome do cliente
     * @return String - Nome do cliente */
    public String getNome() {
        return nome;
    }

    /** Método usado para setar o nome do cliente
     * @param nome String - Seta o nome do cliente */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** Método que retorna o sobrenome do cliente
     * @return String - Sobrenome do cliente */
    public String getSobrenome() {
        return sobrenome;
    }

    /** Método usado para setar o sobrenome do cliente
     * @param sobrenome String - Seta o sobrenome do cliente */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /** Método que retorna o rg do cliente
     * @return String - rg do cliente */
    public String getRg() {
        return rg;
    }

    /** Método usado para setar o rg do cliente
     * @param rg String - Seta o rg do cliente */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /** Método que retorna o cpf do cliente
     * @return String - cpf do cliente */
    public String getCpf() {
        return cpf;
    }

    /** Métoddo usado para setar o cpf do cliente
     * @param cpf String - Seta o cpf do cliente */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /** Método que retorna a data de nascimento do cliente
     * @return String - Data de nascimento do cliente */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /** Método usado para setar a data de nascimento do cliente
     * @param dataNascimento String - Seta a data de nascimento do cliente */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /** Método que retorna o plano do cliente
     * @return Plano - Plano do cliente, com seus atributos */
    public Plano getPlano() {
        return plano;
    }

    /** Método utilizado para setar o plano do cliente
     * @param plano Plano - Seta o plano do cliente */
    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    /** Método que retorna a lista de pagamentos do cliente
     * @return List - Lista de pagamentos do cliente */
    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    /** Método utilizado para setar a lista de pagamentos do cliente
     * @param pagamentos List - Seta a lista de pagamentos do cliente */
    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    /** Método que retorna a data de cadastro do cliente
     * @return String - Data de cadastro do cliente */
    public String getDataCadastro() {
        return dataCadastro;
    }

    /** Método utilizado para setar a data de cadastro do cliente
     * @param dataCadastro String - Seta a data de cadastro do cliente */
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
