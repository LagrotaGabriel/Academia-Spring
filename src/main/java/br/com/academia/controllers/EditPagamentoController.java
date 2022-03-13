package br.com.academia.controllers;

import br.com.academia.models.entities.Cliente;
import br.com.academia.models.entities.Pagamento;
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

@Controller
@RequestMapping("/pagamentos")
public class EditPagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    ClienteService clienteService;

    @GetMapping("/edit-{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Model model, ModelAndView modelAndView){
        model.addAttribute("pagamento", pagamentoService.byId(id));
        modelAndView.setViewName("editpagamento");
        return modelAndView;
    }

    @PostMapping("/edit-{id}")
    public ModelAndView editPost(@PathVariable("id") Long id, Pagamento pagamento, Model model,
                                 ModelAndView modelAndView, RedirectAttributes redirAttrs){

        Cliente clientePgto = pagamentoService.byId(id).getCliente();
        Pagamento updated = pagamentoService.byId(id);
        int indice = clientePgto.getPagamentos().indexOf(updated);
        updated.setValor(pagamento.getValor());
        updated.setFormaPagamento(pagamento.getFormaPagamento());
        updated.setDesconto(pagamento.getDesconto());
        updated.setModalidade(pagamento.getModalidade());
        clientePgto.getPagamentos().set(indice, updated);
        clienteService.update(clientePgto.getId(), clientePgto);
        pagamentoService.update(id, updated);
        redirAttrs.addFlashAttribute("UsuarioCadastrado", "Pagamento alterado com sucesso");
        modelAndView.setViewName("redirect:/pagamentos");
        return modelAndView;

    }

}
