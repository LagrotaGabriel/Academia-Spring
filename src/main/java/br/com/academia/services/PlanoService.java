package br.com.academia.services;

import br.com.academia.exceptions.NaoEncontrado;
import br.com.academia.models.entities.Plano;
import br.com.academia.repositories.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** Classe para processamentos de objetos do tipo plano, como o CRUD ou demais buscas e validações
 * @since 14/03/2022
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0 */
@Service
public class PlanoService {

    // Injeção de dependência para invocar métodos da interface PlanoRepository
    @Autowired
    PlanoRepository planoRepository;

    /** Método que tem como objetivo ciar um novo plano no banco de dados
     * @param plano Plano - Recebe o plano que será criado
     * @return Plano - Retorna o plano que foi criado
     */
    public Plano create(Plano plano){
        return planoRepository.save(plano);
    }

    /** Método que tem como objetivo retornar todos os planos salvos no banco dados em uma Lista
     * @return List - Todos os planos do banco de dados */
    public List<Plano> readAll(){
        return planoRepository.findAll();
    }

    /** Método que tem como objetivo encontrar um plano no banco de dados através do ID recebido pelo parâmetro
     * @param id Long - Identificação do plano
     * @return Plano - Retorna o plano encontrado através do id recebido no parâmetro */
    public Plano byId(Long id){
        return planoRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Plano não encontrado"));
    }

    /** Método que tem como objetivo atualizar um plano já cadastrado no banco de dados, encontrando-o através do id
     * recebido pelo parâmetro e atualizando-o através do objeto plano recebido pelo parâmetro
     * @param id - Long - Identificação do plano
     * @param plano - Plano - Novo objeto plano a ser atualizado
     * @return Plano - Retorna o plano atualizado */
    public Plano update(Long id, Plano plano){
        try{
            Plano finded = byId(id);
            finded.setTipoPlano(plano.getTipoPlano());
            finded.setDataAssinatura(plano.getDataAssinatura());
            return create(finded);
        }
        catch (Exception e){
            return null;
        }
    }

    /** Método que tem como objetivo deletar um plano já cadastrado no banco de dados, encontrando-o através do id
     * recebido pelo parâmetro
     * @param id - Long - Identificação do plano
     * @return Boolean - Retorna true ou false dependendo se o plano foi apagado ou não*/
    public Boolean delete(Long id){
        try{
            planoRepository.delete(byId(id));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

}
