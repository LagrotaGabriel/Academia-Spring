package br.com.academia.controllers;

import br.com.academia.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pagamentos")
public class VerPagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping("/view-{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model, ModelAndView modelAndView){

        model.addAttribute("pagamento", pagamentoService.byId(id));
        modelAndView.setViewName("verpagamento");
        return modelAndView;
    }

}
