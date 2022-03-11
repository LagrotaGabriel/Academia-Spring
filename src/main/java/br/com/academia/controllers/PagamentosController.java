package br.com.academia.controllers;

import br.com.academia.models.entities.Pagamento;
import br.com.academia.repositories.PagamentoRepository;
import br.com.academia.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class PagamentosController {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping("/pagamentos")
    public ModelAndView pagamentosGet(Model model, ModelAndView modelAndView){

        List<Pagamento> pagamentos = pagamentoService.readAll();
        model.addAttribute("pagamentos", pagamentos);
        modelAndView.setViewName("pagamentos");
        return modelAndView;
    }

    @PostMapping("/pagamentos")
    public ModelAndView pagamentosPost
            (Pagamento pagamento, Model model, ModelAndView modelAndView, RedirectAttributes redirAttrs){


        modelAndView.setViewName("redirect:pagamentos");
        return modelAndView;
    }

}
