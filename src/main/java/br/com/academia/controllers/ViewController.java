package br.com.academia.controllers;

import br.com.academia.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cliente/")
public class ViewController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("view-{id}")
    public ModelAndView viewGet(@PathVariable("id") Long id, ModelAndView modelAndView, Model model){

        model.addAttribute("cliente", clienteService.byId(id));
        modelAndView.setViewName("ver");
        return modelAndView;

    }

}
