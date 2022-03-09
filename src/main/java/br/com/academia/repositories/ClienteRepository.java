package br.com.academia.repositories;

import br.com.academia.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("Select c From Cliente c where c.rg = ?1")
    Optional<Cliente> findByRg(@Param("rg") String rg);

    @Query("Select c From Cliente c where c.nome = ?1")
    Optional<Cliente> findByNome(@Param("nome") String nome);

    @Query("Select c From Cliente c where c.cpf = ?1")
    Optional<Cliente> findByCpf(@Param("cpf") String cpf);

}
