package br.com.academia.controllers;

import br.com.academia.models.entities.Cliente;
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

@Controller
@RequestMapping("cliente/")
public class EditClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("edit-{id}")
    public ModelAndView alterar(@PathVariable("id") Long id, ModelAndView modelAndView, Model model){
        System.err.println("Acessei o método alterar");
        model.addAttribute("cliente", clienteService.byId(id));
        modelAndView.setViewName("editcliente");
        return modelAndView;
    }

    @PostMapping("edit-{id}")
    public ModelAndView alterarPost
            (@PathVariable("id") Long id, Cliente cliente, ModelAndView modelAndView,
             Model model, RedirectAttributes redirAttrs){

        System.err.println("Acessei o método alterarPost");

        Cliente updated = clienteService.byId(id);
        updated.setNome(cliente.getNome());
        updated.setSobrenome(cliente.getSobrenome());
        updated.setRg(cliente.getRg());
        updated.setCpf(cliente.getCpf());
        updated.setDataNascimento(cliente.getDataNascimento());
        updated.setId(cliente.getId());
        updated.getPlano().setTipoPlano(cliente.getPlano().getTipoPlano());

        System.err.println(clienteService.update(id, updated));
        System.err.println(updated.getPlano());

        if(clienteService.update(id, updated) != null && updated.getPlano() != null){
            redirAttrs.addFlashAttribute("UsuarioCadastrado",
                    "Usuário atualizado com sucesso");
        }
        else{
            redirAttrs.addFlashAttribute("UsuarioCadastrado",
                    "Falha na atualização do usuário");
        }

        modelAndView.setViewName("redirect:edit-"+id);
        return modelAndView;
    }


}
