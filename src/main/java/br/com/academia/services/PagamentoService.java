package br.com.academia.services;

import br.com.academia.exceptions.NaoEncontrado;
import br.com.academia.models.entities.Pagamento;
import br.com.academia.repositories.PagamentoRepository;
import br.com.academia.validations.PagamentoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    PagamentoValidation pagamentoValidation = new PagamentoValidation();

    public Pagamento create(Pagamento pagamento){
        if(pagamentoValidation.validaValor(pagamento.getValor())) return pagamentoRepository.save(pagamento);
        return null;
    }

    public List<Pagamento> readAll(){
        return pagamentoRepository.findAll();
    }

    public Pagamento byId(Long id){
        return pagamentoRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Id não encontrado"));
    }

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
