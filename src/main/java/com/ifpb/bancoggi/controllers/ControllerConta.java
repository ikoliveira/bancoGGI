package com.ifpb.bancoggi.controllers;

import com.ifpb.bancoggi.entidades.Cliente;
import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryConta;
import com.ifpb.bancoggi.service.ServiceConta;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/conta")
public class ControllerConta {

    private ServiceConta serviceConta;

    public void requisitaCriacaoConta(String senha, Integer numConta){
        serviceConta.preparaRegistroConta(senha, numConta);
    }

    @RequestMapping("/saldo")
    public Double solicitaSaldo(Integer numConta){
        return serviceConta.retornaSaldo(numConta);
    }

    public Integer retornaNumConta(String cpf){
        //ainda não existe essa relacao
        return 1;
    }

    public String retornaNomeCliente(String cpf){
        //ainda não existe essa relacao
        return "joaquim";
    }

    @RequestMapping("/saque")
    public void realizaSaque(Integer numConta, Double valor){
        serviceConta.saque(numConta, valor);
    }

    @RequestMapping("/deposito")
    public void recebeDeposito(Integer numConta, Double valor){
        serviceConta.deposito(numConta, valor);
    }

    @RequestMapping("/informacoesConta")
    public String exibeInformacoes(Integer numConta) {
        return serviceConta.localizaConta(numConta).toString();
    }

}
