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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("/")
public class CadPagamentoController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping("pagamentos/novo-{id}")
    public ModelAndView cadClienteGet(@PathVariable("id") Long id,ModelAndView modelAndView, Model model){
        model.addAttribute("clientePgto", clienteService.byId(id));
        modelAndView.setViewName("cadpagamento");
        return modelAndView;
    }

    @PostMapping("/novo-{id}")
    public ModelAndView cadClientePost
            (@PathVariable("id") Long id, Pagamento pagamento,
             ModelAndView modelAndView, Model model, RedirectAttributes redirAttrs){

        Cliente cliente = clienteService.byId(id);

        pagamento.setCliente(clienteService.byId(id));
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        pagamento.setVencimento(calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));

        System.err.println("VALOR: " + pagamento.getValor());
        System.err.println("DESCONTO: " + pagamento.getDesconto());
        System.err.println("MODALIDADE: " + pagamento.getModalidade());

        cliente.getPagamentos().add(pagamento);
        clienteService.update(id, cliente);

        System.err.println("PAGAMENTOS: ");
        System.err.println(clienteService.byId(id).getPagamentos());

        redirAttrs.addFlashAttribute("UsuarioCadastrado", "Pagamento cadastrado com sucesso!");
        modelAndView.setViewName("redirect:cliente/todos");
        return modelAndView;
    }



}
