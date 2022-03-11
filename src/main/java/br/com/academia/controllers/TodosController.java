package br.com.academia.controllers;

import br.com.academia.models.entities.Cliente;
import br.com.academia.repositories.ClienteRepository;
import br.com.academia.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class TodosController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/todos")
    public ModelAndView todos(Model model){

        System.err.println("Acessei o todos");
        ModelAndView modelAndView = new ModelAndView();
        List<Cliente> clientes = clienteService.readAll();
        model.addAttribute("clientes", clientes);
        modelAndView.setViewName("all");
        return modelAndView;
    }

    @PostMapping("/todos")
    public ModelAndView todosPost(Cliente cliente, Model model, RedirectAttributes redirAttrs){

        System.err.println("Acessei o todosPost");
        ModelAndView modelAndView = new ModelAndView();

        cliente.setRg(cliente.getRg().replaceAll("\\.", "").replaceAll("-", ""));
        cliente.setCpf(cliente.getCpf().replaceAll("\\.", "").replaceAll("-", ""));

        // SE OS DOIS CAMPOS CONSTAM ALGO E O CLIENTE RETORNADO FOR IGUAL
        if (clienteService.byRg(cliente.getRg()).isPresent() && clienteService.byCpf(cliente.getCpf()).isPresent()
        && clienteService.byRg(cliente.getRg()).get() == clienteService.byCpf(cliente.getCpf()).get()){
            modelAndView.setViewName("redirect:todos-" + clienteRepository.findByRg(cliente.getRg()).get().getId());
        }
        else{
            redirAttrs.addFlashAttribute
                    ("StatusCadastro", "Usuário não encontrado");
            modelAndView.setViewName("redirect:todos");
        }
        return modelAndView;

    }

    @GetMapping("/todos-{id}")
    public ModelAndView search(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs){

        System.err.println("Acessei o search");
        if(clienteRepository.findById(id).isPresent()){
            Cliente buscado = clienteRepository.findById(id).get();
            System.err.println(buscado);
            List<Cliente> clientes = new ArrayList<>();
            clientes.add(buscado);
            System.err.println(clientes);
            redirAttrs.addFlashAttribute("teste", clientes);
            model.addAttribute("clientes", clientes);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all");
        return modelAndView;
    }

    @PostMapping("/todos-{id}")
    public ModelAndView searchPost(@PathVariable("id") Long id,Cliente cliente, Model model, RedirectAttributes redirAttrs){
        ModelAndView modelAndView = new ModelAndView();

        System.err.println("Acessei o searchPost");
        cliente.setRg(cliente.getRg().replaceAll("\\.", "").replaceAll("-", ""));
        cliente.setCpf(cliente.getCpf().replaceAll("\\.", "").replaceAll("-", ""));

        // SE OS DOIS CAMPOS CONSTAM ALGO E O CLIENTE RETORNADO FOR IGUAL
        if (clienteService.byRg(cliente.getRg()).isPresent() && clienteService.byCpf(cliente.getCpf()).isPresent()
                && clienteService.byRg(cliente.getRg()).get() == clienteService.byCpf(cliente.getCpf()).get()){
            modelAndView.setViewName("redirect:todos-" + clienteRepository.findByRg(cliente.getRg()).get().getId());
        }
        else{
            redirAttrs.addFlashAttribute
                    ("StatusCadastro", "Usuário não encontrado");
            modelAndView.setViewName("redirect:todos");
        }
        return modelAndView;
    }

    @PostMapping("/delete-{id}")
    public ModelAndView delete(@PathVariable("id") Long id, ModelAndView modelAndView, RedirectAttributes redirAttrs){

        redirAttrs.addFlashAttribute
                ("StatusCadastro", "Cliente " +
                        clienteService.byId(id).getNome() +
                        " " +
                        clienteService.byId(id).getSobrenome() + " deletado com sucesso");
        clienteService.delete(id);
        modelAndView.setViewName("redirect:todos");
        return modelAndView;
    }

    @GetMapping("/todos/view={id}")
    public ModelAndView testando(@PathVariable("id") Long id ,ModelAndView modelAndView){

        System.err.println("ID: " + id);
        modelAndView.setViewName("all");
        return modelAndView;

    }

}
