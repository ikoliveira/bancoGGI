package com.ifpb.bancoggi.controllers;

import com.ifpb.bancoggi.entidades.Cliente;
import com.ifpb.bancoggi.entidades.Conta;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ControllerConta {

    Date data;
    Conta conta = new Conta(123, "xxxx", 20.20, true, data);

    @RequestMapping("/saque")
    public Double saque() {
        Double valor = 15.0;
        return conta.saque(valor);
    }

    @RequestMapping("/informacoesConta")
    public String exibeInformacoes() {
        conta.toString();

        return  conta.toString();
    }

}
