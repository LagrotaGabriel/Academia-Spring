package br.com.academia.controllers;

import br.com.academia.models.entities.Cliente;
import br.com.academia.models.entities.Pagamento;
import br.com.academia.repositories.ClienteRepository;
import br.com.academia.repositories.PagamentoRepository;
import br.com.academia.services.ClienteService;
import br.com.academia.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PagamentosController {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping("pagamentos")
    public ModelAndView pagamentosGet(Model model, ModelAndView modelAndView){

        List<Pagamento> pagamentosAll = pagamentoService.readAll();
        model.addAttribute("pagamentosAll", pagamentosAll);
        modelAndView.setViewName("pagamentos");
        return modelAndView;
    }

    @PostMapping("pagamentos")
    public ModelAndView pagamentosPost
            (Cliente cliente, Model model, ModelAndView modelAndView, RedirectAttributes redirAttrs){

        System.err.println("Método pagamentosPost acessado");

        cliente.setRg(cliente.getRg().replaceAll("\\.", "").replaceAll("-", ""));
        cliente.setCpf(cliente.getCpf().replaceAll("\\.", "").replaceAll("-", ""));

        System.err.println(cliente.getRg());
        System.err.println(cliente.getCpf());

        // SE OS DOIS CAMPOS CONSTAM ALGO E O CLIENTE RETORNADO FOR IGUAL
        if (clienteService.byRg(cliente.getRg()).isPresent() && clienteService.byCpf(cliente.getCpf()).isPresent()
                && clienteService.byRg(cliente.getRg()).get() == clienteService.byCpf(cliente.getCpf()).get()){
            modelAndView.setViewName("redirect:pagamentos-" + clienteRepository.findByRg(cliente.getRg()).get().getId());
        }
        else{
            redirAttrs.addFlashAttribute
                    ("StatusCadastro", "Usuário não encontrado");
            modelAndView.setViewName("redirect:pagamentos");
        }
        return modelAndView;

    }

    @GetMapping("pagamentos-{id}")
    public ModelAndView search(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs){

        System.err.println("Acessei o searchGet");
        if(clienteRepository.findById(id).isPresent()){

            List<Pagamento> pagamentosAll = clienteRepository.findById(id).get().getPagamentos();
            redirAttrs.addFlashAttribute("pagamentosAll", pagamentosAll);
            model.addAttribute("pagamentosAll", pagamentosAll);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pagamentos");
        return modelAndView;
    }

    @PostMapping("pagamentos-{id}")
    public ModelAndView searchPost(@PathVariable("id") Long id,Cliente cliente, Model model, RedirectAttributes redirAttrs){
        ModelAndView modelAndView = new ModelAndView();

        System.err.println("Acessei o searchPost");
        cliente.setRg(cliente.getRg().replaceAll("\\.", "").replaceAll("-", ""));
        cliente.setCpf(cliente.getCpf().replaceAll("\\.", "").replaceAll("-", ""));

        // SE OS DOIS CAMPOS CONSTAM ALGO E O CLIENTE RETORNADO FOR IGUAL
        if (clienteService.byRg(cliente.getRg()).isPresent() && clienteService.byCpf(cliente.getCpf()).isPresent()
                && clienteService.byRg(cliente.getRg()).get() == clienteService.byCpf(cliente.getCpf()).get()){
            modelAndView.setViewName("redirect:pagamentos-" + clienteRepository.findByRg(cliente.getRg()).get().getId());
        }
        else{
            redirAttrs.addFlashAttribute
                    ("StatusCadastro", "Usuário não encontrado");
            modelAndView.setViewName("redirect:pagamentos");
        }
        return modelAndView;
    }

    @PostMapping("/del-{id}")
    public ModelAndView delete(@PathVariable("id") Long id, ModelAndView modelAndView, RedirectAttributes redirAttrs){

        Pagamento pagamento = pagamentoService.byId(id);
        Cliente cliente = clienteService.byId(pagamento.getCliente().getId());

        pagamentoService.delete(id);
        System.err.println("Antiga lista de pagamentos: " + cliente.getPagamentos().indexOf(pagamento));
        cliente.getPagamentos().remove(pagamento);
        System.err.println("Nova lista de pagamentos: " + cliente.getPagamentos().indexOf(pagamento));
        clienteService.update(cliente.getId(), cliente);
        redirAttrs.addFlashAttribute
                ("StatusCadastro", "Pagamento deletado com sucesso");
        pagamentoService.delete(id);
        modelAndView.setViewName("redirect:pagamentos");
        return modelAndView;
    }
}
