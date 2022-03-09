package br.com.academia.controllers;

import br.com.academia.models.entities.Cliente;
import br.com.academia.repositories.ClienteRepository;
import br.com.academia.services.ClienteService;
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
import java.util.Optional;

@Controller
@RequestMapping("/")
public class TodosController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("todos")
    public ModelAndView todos(Model model){

        ModelAndView modelAndView = new ModelAndView();
        List<Cliente> clientes = clienteService.readAll();
        model.addAttribute("clientes", clientes);
        modelAndView.setViewName("all");
        return modelAndView;
    }

    @PostMapping("todos")
    public ModelAndView todosPost(Cliente cliente, Model model, RedirectAttributes redirAttrs){

        ModelAndView modelAndView = new ModelAndView();

        // CPF VAZIO RG PREENCHIDO
        if(cliente.getCpf().equals("") || cliente.getCpf() == null && !cliente.getRg().equals("")){
            cliente.setRg(cliente.getRg().replaceAll("-","").replaceAll("\\.",""));
            modelAndView.setViewName("redirect:/todos="+ clienteRepository.findByRg(cliente.getRg()).get().getId());
        }
        // RG VAZIO CPF PREENCHIDO
        else if(cliente.getRg().equals("") || cliente.getRg() == null && !cliente.getCpf().equals("")){
            cliente.setCpf(cliente.getCpf().replaceAll("-","").replaceAll("\\.",""));
            modelAndView.setViewName("redirect:/todos="+ clienteRepository.findByCpf(cliente.getCpf()).get().getId());
        }
        // AMBOS VAZIOS
        else if(cliente.getCpf().equals("") || cliente.getCpf() == null && cliente.getRg().equals("") || cliente.getRg() == null) {
            redirAttrs = null;
            modelAndView.setViewName("redirect:/todos");
        }
        // AMBOS PREENCHIDOS
        else if(!cliente.getRg().equals("") && !cliente.getCpf().equals("")){
            cliente.setRg(cliente.getRg().replaceAll("-","").replaceAll("\\.",""));
            cliente.setCpf(cliente.getCpf().replaceAll("-","").replaceAll("\\.",""));
            System.err.println("1");
            // SE O RG ESTIVER OK
            if(clienteService.byRg(cliente.getRg()).isPresent()){
                System.err.println("2");
                cliente.setRg(cliente.getRg().replaceAll("-","").replaceAll("\\.",""));
                modelAndView.setViewName("redirect:/todos="+ clienteRepository.findByRg(cliente.getRg()).get().getId());
            }

            // SE O RG NÃO ESTIVER OK
            else{

                // SE O CPF ESTIVER OK
                System.err.println("3");
                if(clienteService.byCpf(cliente.getCpf()).isPresent()){
                    cliente.setCpf(cliente.getCpf().replaceAll("-","").replaceAll("\\.",""));
                    modelAndView.setViewName("redirect:/todos="+ clienteRepository.findByCpf(cliente.getCpf()).get().getId());
                }

                // SE O CPF NÃO ESTIVER OK
                else{
                    System.err.println("4");
                    redirAttrs = null;
                    modelAndView.setViewName("redirect:/todos");
                }
            }
        }
        return modelAndView;
    }

    @GetMapping("todos={id}")
    public ModelAndView search(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs){

        System.err.println("ACESSEI");
        System.err.println(id);
        if(clienteRepository.findById(id).isPresent()){
            System.err.println("PRESENTE");
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

    @PostMapping("todos={id}")
    public ModelAndView searchPost(@PathVariable("id") Long id,Cliente cliente, Model model, RedirectAttributes redirAttrs){

        ModelAndView modelAndView = new ModelAndView();

        // CPF VAZIO RG PREENCHIDO
        if(cliente.getCpf().equals("") || cliente.getCpf() == null && !cliente.getRg().equals("")){
            cliente.setRg(cliente.getRg().replaceAll("-","").replaceAll("\\.",""));
            modelAndView.setViewName("redirect:/todos="+ clienteRepository.findByRg(cliente.getRg()).get().getId());
        }
        // RG VAZIO CPF PREENCHIDO
        else if(cliente.getRg().equals("") || cliente.getRg() == null && !cliente.getCpf().equals("")){
            cliente.setCpf(cliente.getCpf().replaceAll("-","").replaceAll("\\.",""));
            modelAndView.setViewName("redirect:/todos="+ clienteRepository.findByCpf(cliente.getCpf()).get().getId());
        }
        // AMBOS VAZIOS
        else if(cliente.getCpf().equals("") || cliente.getCpf() == null && cliente.getRg().equals("") || cliente.getRg() == null){
            redirAttrs = null;
            modelAndView.setViewName("redirect:/todos");
        }
        // AMBOS PREENCHIDOS
        else{
            cliente.setRg(cliente.getRg().replaceAll("-","").replaceAll("\\.",""));
            cliente.setCpf(cliente.getCpf().replaceAll("-","").replaceAll("\\.",""));
            // SE O RG ESTIVER OK
            if(clienteService.byRg(cliente.getRg()).isPresent()){
                cliente.setRg(cliente.getRg().replaceAll("-","").replaceAll("\\.",""));
                modelAndView.setViewName("redirect:/todos="+ clienteRepository.findByRg(cliente.getRg()).get().getId());
            }

            // SE O RG NÃO ESTIVER OK
            else{

                // SE O CPF ESTIVER OK
                if(clienteService.byCpf(cliente.getCpf()).isPresent()){
                    cliente.setCpf(cliente.getCpf().replaceAll("-","").replaceAll("\\.",""));
                    modelAndView.setViewName("redirect:/todos="+ clienteRepository.findByCpf(cliente.getCpf()).get().getId());
                }

                // SE O CPF NÃO ESTIVER OK
                else{
                    redirAttrs = null;
                    modelAndView.setViewName("redirect:/todos");
                }
            }
        }
        return modelAndView;
    }


}
