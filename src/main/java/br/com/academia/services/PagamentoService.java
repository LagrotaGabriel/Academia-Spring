package br.com.academia.services;

import br.com.academia.exceptions.NaoEncontrado;
import br.com.academia.models.entities.Pagamento;
import br.com.academia.repositories.PagamentoRepository;
import br.com.academia.validations.PagamentoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** Classe para processamentos de objetos do tipo Pagamento, como o CRUD ou demais buscas e validações
 * @since 14/03/2022
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0 */
@Service
public class PagamentoService {

    // Injeção de dependência para invocar métodos da interface PagamentoRepository
    @Autowired
    PagamentoRepository pagamentoRepository;

    // Instanciação da classe de validação de entrada de dados do pagamento PagamentoValidation
    PagamentoValidation pagamentoValidation = new PagamentoValidation();

    /** Método que tem como objetivo ciar um novo pagamento no banco de dados
     * @param pagamento Pagamento - Recebe o pagamento que será criado
     * @return Pagamento - Retorna o pagamento que foi criado
     */
    public Pagamento create(Pagamento pagamento){
        if(pagamentoValidation.validaValor(pagamento.getValor())) return pagamentoRepository.save(pagamento);
        return null;
    }

    /** Método que tem como objetivo retornar todos os pagamentos salvos no banco dados em uma Lista
     * @return List - Todos os pagamentos do banco de dados */
    public List<Pagamento> readAll(){
        return pagamentoRepository.findAll();
    }

    /** Método que tem como objetivo encontrar um pagamento no banco de dados através do ID recebido pelo parâmetro
     * @param id Long - Identificação do pagamento
     * @return Pagamento - Retorna o pagamento encontrado através do id recebido no parâmetro */
    public Pagamento byId(Long id){
        return pagamentoRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Id não encontrado"));
    }

    /** Método que tem como objetivo atualizar um pagamento já cadastrado no banco de dados, encontrando-o através do id
     * recebido pelo parâmetro e atualizando-o através do objeto pagamento recebido pelo parâmetro
     * @param id - Long - Identificação do pagamento
     * @param pagamento - Pagamento - Novo objeto pagamento a ser atualizado
     * @return Pagamento - Retorna o pagamento atualizado */
    public Pagamento update(Long id, Pagamento pagamento){

        try{

            if(pagamentoValidation.validaValor(pagamento.getValor())) {
                Pagamento finded = byId(id);
                finded.setValor(pagamento.getValor());
                finded.setFormaPagamento(pagamento.getFormaPagamento());
                finded.setDesconto(pagamento.getDesconto());
                finded.setModalidade(pagamento.getModalidade());
                finded.setVencimento(pagamento.getVencimento());
                return create(finded);
            }

            else{
                return null;
            }

        }
        catch(Exception e){
            return null;
        }
    }

    /** Método que tem como objetivo deletar um pagamento já cadastrado no banco de dados, encontrando-o através do id
     * recebido pelo parâmetro
     * @param id - Long - Identificação do pagamento
     * @return Boolean - Retorna true ou false dependendo se o pagamento foi apagado ou não*/
    public Boolean delete(Long id){

        try {
            if (byId(id) != null) {
                pagamentoRepository.deleteById(id);
                pagamentoRepository.delete(byId(id));
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e){
            return false;
        }

    }

}
