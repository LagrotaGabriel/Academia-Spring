package br.com.academia.controllers.apis;

import br.com.academia.models.entities.Pagamento;
import br.com.academia.services.PagamentoService;
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
@RequestMapping("api/pagamento")
@Api(value = "Acamidemia API - Pagamento Resource")
public class PagamentoResource {

    @Autowired
    PagamentoService pagamentoService;

    @RequestMapping(method = RequestMethod.POST, path = "/new")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Cria um novo pagamento no banco de dados")
    public ResponseEntity<Pagamento> criaPagamento(@RequestBody Pagamento pagamento){
        try{
            if(pagamentoService.create(pagamento) != null) return ResponseEntity.ok().body(pagamentoService.create(pagamento));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Lista todos os pagamentos cadastrados no banco de dados")
    public ResponseEntity<List<Pagamento>> listaTodos(){
        if(pagamentoService.readAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(pagamentoService.readAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/id={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Retorna pagamento pelo id")
    public ResponseEntity<Pagamento> retornaPorId(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok().body(pagamentoService.byId(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Atualiza pagamento pelo id")
    public ResponseEntity<Pagamento> atualiza(@PathVariable("id") Long id, @RequestBody Pagamento pagamento){
        if(pagamentoService.update(id, pagamento) != null) return ResponseEntity.ok().body(pagamento);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete={id}")
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @ApiOperation(value = "Deleta um pagamento pelo id")
    public ResponseEntity<Pagamento> deleta(@PathVariable("id") Long id){
        if(pagamentoService.delete(id) != null) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
