package br.com.academia.services;

import br.com.academia.exceptions.NaoEncontrado;
import br.com.academia.models.entities.Cliente;
import br.com.academia.repositories.ClienteRepository;
import br.com.academia.repositories.PagamentoRepository;
import br.com.academia.validations.ClienteValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PagamentoRepository pagamentoRepository;
    ClienteValidation clienteValidation = new ClienteValidation();

    public Cliente create(Cliente cliente){

        if(clienteValidation.validaCliente(cliente)){
            return clienteRepository.save(cliente);
        }
        else{
            return null;
        }

    }

    public List<Cliente> readAll(){
        return clienteRepository.findAll();
    }

    public Cliente byId(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Cliente não encontrado"));
    }

    public Optional<Cliente> byRg(String rg){
        return clienteRepository.findByRg(rg);
    }

    public Optional<Cliente> byCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }

    public Cliente update(Long id, Cliente cliente){

        try{
            if(clienteValidation.validaCliente(cliente)) {
                Cliente finded = byId(id);
                finded.setNome(cliente.getNome());
                finded.setSobrenome(cliente.getSobrenome());
                finded.setRg(cliente.getRg());
                finded.setCpf(cliente.getCpf());
                finded.setDataNascimento(cliente.getDataNascimento());
                finded.setDataCadastro(cliente.getDataCadastro());

                if (cliente.getPlano() != null) {

                    if (finded.getPlano() != null) {

                        finded.getPlano().setCliente(cliente.getPlano().getCliente());
                        finded.getPlano().setTipoPlano(cliente.getPlano().getTipoPlano());
                        finded.getPlano().setDataAssinatura(cliente.getPlano().getDataAssinatura());
                    }
                    else {
                        finded.setPlano(cliente.getPlano());
                    }
                }
                if (cliente.getPagamentos() != null) finded.setPagamentos(cliente.getPagamentos());
                return create(finded);
            }
            else{
                System.err.println("Entrou no null");
                return null;
            }
        }
        catch(Exception e){
            System.err.println("Entrou no Catch");
            return null;
        }
    }

    public Boolean delete(Long id){

        try{
            Cliente clienteDeleted = byId(id);
            clienteRepository.delete(clienteDeleted);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

}
