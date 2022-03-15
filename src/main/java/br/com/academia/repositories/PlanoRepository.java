package br.com.academia.repositories;

import br.com.academia.models.entities.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Interface que extende os métodos CRUD do JpaRepository relacionados a objetos do tipo Plano
 * @since 15/03/2022 - 08:53am
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0 */
@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> { }
