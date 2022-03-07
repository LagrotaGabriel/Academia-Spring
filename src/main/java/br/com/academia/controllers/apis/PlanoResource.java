package br.com.academia.controllers.apis;

import br.com.academia.models.entities.Cliente;
import br.com.academia.models.entities.Plano;
import br.com.academia.services.ClienteService;
import br.com.academia.services.PlanoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("api/plano")
@Api(value = "Academia API - Plano Resource")
public class PlanoResource {

    @Autowired
    PlanoService planoService;

    @Autowired
    ClienteService clienteService;

    @RequestMapping(method = RequestMethod.POST, path = "/new")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Cria um novo plano no banco de dados")
    public ResponseEntity<Cliente> criaPlano(@RequestBody Plano plano){

        try {
            if (plano.getIdCliente() != null) {

                    Cliente clientePlano = clienteService.byId(Long.parseLong(plano.getIdCliente().toString()));
                    clientePlano.setPlano(plano);
                    return ResponseEntity.ok().body(clienteService.update(clientePlano.getId(), clientePlano));

            }

            else {
                return ResponseEntity.badRequest().build();
            }

        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Lista todos os planos cadastrados no banco de dados")
    public ResponseEntity<List<Plano>> listaTodos(){
        if(planoService.readAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(planoService.readAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/id={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Retorna um plano cadastrado pelo ID")
    public ResponseEntity<Plano> retornaPorId(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok().body(planoService.byId(id));
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Atualiza um plano cadastrado pelo ID")
    public ResponseEntity<Cliente> atualizaPlano(@PathVariable("id") Long id, @RequestBody Plano plano){
        try {
            if (plano.getIdCliente() != null) {
                Plano finded = planoService.update(id, plano);

                Cliente clientePlano = clienteService.byId(Long.parseLong(plano.getIdCliente().toString()));
                clientePlano.setPlano(plano);
                return ResponseEntity.ok().body(clienteService.update(clientePlano.getId(), clientePlano));
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Deleta um plano cadastrado pelo ID")
    public ResponseEntity<Plano> deletaPlano(@PathVariable("id") Long id){
        if(planoService.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
