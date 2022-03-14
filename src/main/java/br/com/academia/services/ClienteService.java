package br.com.academia.services;

import br.com.academia.exceptions.NaoEncontrado;
import br.com.academia.models.entities.Cliente;
import br.com.academia.repositories.ClienteRepository;
import br.com.academia.validations.ClienteValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Classe para processamentos de objetos do tipo Cliente, como o CRUD ou demais buscas e validações
 * @since 13/03/2022
 * @author gahcruz ~ Gabriel Lagrota
 * @version 1.0 */
@Service
public class ClienteService {

    // Injeção de dependência para invocar métodos da interface ClienteRepository
    @Autowired
    ClienteRepository clienteRepository;

    // Instanciação da classe de validação de entrada de dados do cliente ClienteValidation
    ClienteValidation clienteValidation = new ClienteValidation();

    /** Método que tem como objetivo ciar um novo cliente no banco de dados
     * @param cliente Cliente - Recebe o cliente que será criado
     * @return Cliente - Retorna o cliente que foi criado
     */
    public Cliente create(Cliente cliente){
        // Se o cliente estiver validado de acordo com a classe cliente validation a estrutura condicional é acessada
        if(clienteValidation.validaCliente(cliente)){
            // Retorna o objeto cliente já criado no banco de dados
            return clienteRepository.save(cliente);
        }
        // Se o cliente não estiver validado
        else{
            // Retorna nulo
            return null;
        }
    }

    /** Método que tem como objetivo retornar todos os clientes salvos no banco dados em uma Lista
     * @return List - Todos os clientes do banco de dados */
    public List<Cliente> readAll(){
        return clienteRepository.findAll();
    }

    /** Método que tem como objetivo encontrar um cliente no banco de dados através do ID recebido pelo parâmetro
     * @param id Long - Identificação do cliente
     * @return Cliente - Retorna o cliente encontrado através do id recebido no parâmetro */
    public Cliente byId(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Cliente não encontrado"));
    }

    /** Método que tem como objetivo encontrar um cliente no banco de dados através do RG recebido pelo parâmetro
     * @param rg - String - RG do cliente
     * @return Cliente - Se um cliente for encontrado no banco de dados através do rg recebido pelo parâmetro,
     * é retornado então o Cliente correspondente ao rg */
    public Optional<Cliente> byRg(String rg){
        return clienteRepository.findByRg(rg);
    }

    /** Método que tem como objetivo encontrar um cliente no banco de dados através do CPF recebido pelo parâmetro
     * @param cpf - String - CPF do cliente
     * @return Cliente - Se um cliente for encontrado no banco de dados através do cpf recebido pelo parâmetro,
     * é retornado então o Cliente correspondente ao cpf*/
    public Optional<Cliente> byCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }

    /** Método que tem como objetivo atualizar um cliente já cadastrado no banco de dados, encontrando-o através do id
     * recebido pelo parâmetro e atualizando-o através do objeto cliente recebido pelo parâmetro
     * @param id - Long - Identificação do cliente
     * @param cliente - Cliente - Novo objeto cliente a ser atualizado
     * @return Cliente - Retorna o cliente atualizado */
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

    /** Método que tem como objetivo deletar um cliente já cadastrado no banco de dados, encontrando-o através do id
     * recebido pelo parâmetro
     * @param id - Long - Identificação do cliente
     * @return Boolean - Retorna true ou false dependendo se o usuário foi apagado ou não*/
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
