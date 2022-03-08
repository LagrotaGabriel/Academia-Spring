package br.com.academia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ClienteController {

    @GetMapping("cliente")
    public ModelAndView cliente(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente");
        return modelAndView;
    }

}
