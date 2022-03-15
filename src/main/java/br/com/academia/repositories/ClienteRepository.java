package br.com.academia.repositories;

import br.com.academia.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/** Interface que extende os métodos CRUD do JpaRepository relacionados a objetos do tipo Cliente
 * @since 15/03/2022 - 08:54am
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /** Query que tem como objetivo localizar um cliente no banco de dados com base no rg recebido pelo parâmetro
     * @param rg - String - RG do cliente
     * @return Cliente - Retorna o cliente encontrado pela busca realizada pelo RG
     */
    @Query("Select c From Cliente c where c.rg = ?1")
    Optional<Cliente> findByRg(@Param("rg") String rg);

    /** Query que tem como objetivo localizar um cliente no banco de dados com base no cpf recebido pelo parâmetro
     * @param cpf - String - CPF do cliente
     * @return Cliente - Retorna o cliente encontrado pela busca realizada pelo CPF */
    @Query("Select c From Cliente c where c.cpf = ?1")
    Optional<Cliente> findByCpf(@Param("cpf") String cpf);

}
