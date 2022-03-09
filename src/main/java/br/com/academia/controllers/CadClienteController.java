package br.com.academia.controllers;

import br.com.academia.models.entities.Cliente;
import br.com.academia.services.ClienteService;
import br.com.academia.validations.ClienteValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
@RestController("/")
public class CadClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("novo")
    public ModelAndView cadCliente(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cadcliente");
        return modelAndView;
    }

    @PostMapping("novo")
    public ModelAndView cadClientePost(Cliente cliente, Model model, RedirectAttributes redirAttrs){

        ModelAndView modelAndView = new ModelAndView();
        ClienteValidation clienteValidation = new ClienteValidation();
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        cliente.setRg(cliente.getRg().replaceAll("\\.", "").replaceAll("-", ""));
        cliente.setCpf(cliente.getCpf().replaceAll("\\.", "").replaceAll("-", ""));

        if(!clienteService.byRg(cliente.getRg()).isPresent() && !clienteService.byCpf(cliente.getCpf()).isPresent()) {
            if (clienteValidation.validaCliente(cliente)) {
                if (clienteValidation.validaDataNascimento(cliente.getDataNascimento())) {

                    cliente.setDataCadastro(calendar.get(Calendar.DAY_OF_MONTH) + "/"
                            + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));

                    cliente.getPlano().setDataAssinatura(calendar.get(Calendar.DAY_OF_MONTH) + "/"
                            + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));

                    clienteService.create(cliente);

                    redirAttrs.addFlashAttribute
                            ("UsuarioCadastrado",
                                    "Cliente " + cliente.getNome() + " "
                                            + cliente.getSobrenome() + " cadastrado com sucesso!");
                } else {
                    redirAttrs.addFlashAttribute("UsuarioCadastrado",
                            "Dados inseridos de forma incorreta");
                }
            }
            else {
                redirAttrs.addFlashAttribute("UsuarioCadastrado",
                        "Dados inseridos de forma incorreta");
            }
        }
        else{
            if(clienteService.byCpf(cliente.getCpf()).isPresent()){
                redirAttrs.addFlashAttribute("UsuarioCadastrado",
                        "Erro: CPF informado já existe");
            }
            else if(clienteService.byRg(cliente.getRg()).isPresent()){
                redirAttrs.addFlashAttribute("UsuarioCadastrado",
                        "Erro: RG informado já existe");
            }
            else if(clienteService.byRg(cliente.getRg()).isPresent() && clienteService.byCpf(cliente.getCpf()).isPresent()){
                redirAttrs.addFlashAttribute("UsuarioCadastrado",
                        "Erro: CPF e RG informados já existem");
            }
        }

        modelAndView.setViewName("redirect:/novo");
        return modelAndView;
    }
}
