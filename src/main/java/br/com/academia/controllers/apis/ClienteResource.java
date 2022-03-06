package br.com.academia.controllers.apis;

import br.com.academia.models.entities.Cliente;
import br.com.academia.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("api/cliente")
@Api(value = "Academia API - Cliente Resource")
public class ClienteResource {

    @Autowired
    ClienteService clienteService;

    @RequestMapping(method = RequestMethod.POST, path = "/new")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Cria um novo cliente no banco de dados")
    public ResponseEntity<Cliente> criaCliente(@RequestBody Cliente cliente){
        try {
            if(clienteService.create(cliente) != null) return ResponseEntity.status(HttpStatus.CREATED).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Lista todos os clientes cadastrados no banco de dados")
    public ResponseEntity<List<Cliente>> listaTodos(){
        if(clienteService.readAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(clienteService.readAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/id={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Lista o cliente pelo id cadastrado")
    public ResponseEntity<Cliente> retornaPorId(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok().body(clienteService.byId(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Atualiza um cliente cadastrado")
    public ResponseEntity<Cliente> atualiza(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        if(clienteService.update(id, cliente) != null) return ResponseEntity.ok().body(cliente);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Apaga um cliente cadastrado")
    public ResponseEntity<Cliente> deleta(@PathVariable("id") Long id){
        if(clienteService.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();

    }

}
