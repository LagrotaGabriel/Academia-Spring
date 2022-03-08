package br.com.academia.controllers;

import br.com.academia.models.entities.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RestController("/")
public class CadClienteController {

    @GetMapping("novo")
    public ModelAndView cadCliente(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cadcliente");
        return modelAndView;
    }

    @PostMapping("novo")
    public ModelAndView cadClientePost(Cliente cliente, Model model, RedirectAttributes redirAttrs){
        ModelAndView modelAndView = new ModelAndView();
        System.err.println("Método post acessado");

        redirAttrs.addFlashAttribute("UsuarioCadastrado",
                "Cliente "+ cliente.getNome() + " " + cliente.getSobrenome() + " cadastrado com sucesso!");

        modelAndView.setViewName("redirect:/novo");
        return modelAndView;
    }
}
