package br.com.academia.controllers;

import br.com.academia.repositories.ClienteRepository;
import br.com.academia.services.ClienteService;
import br.com.academia.services.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PlanosController {

    @Autowired
    PlanoService planoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("planos")
    public ModelAndView planosGet(ModelAndView modelAndView, Model model) {
        model.addAttribute("planos", planoService.readAll());
        modelAndView.setViewName("planos");
        return modelAndView;
    }
}