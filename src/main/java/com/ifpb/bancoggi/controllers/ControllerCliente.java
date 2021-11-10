package com.ifpb.bancoggi.controllers;

import com.ifpb.bancoggi.entidades.Cliente;

import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryCliente;
import com.ifpb.bancoggi.service.ServiceCliente;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class ControllerCliente {

    private ServiceCliente serviceCliente;
    private RepositoryCliente repositoryCliente;

    @Autowired
    public ControllerCliente(ServiceCliente serviceCliente) {
        this.serviceCliente = serviceCliente;
    }

    @PostMapping
    public void requisitaCriacaoCliente(@RequestBody Cliente cliente){
        serviceCliente.criandoCliente(cliente);
    }

    @DeleteMapping("/{cpf}")
    public void solicitaExclusaoCliente(@PathVariable Integer cpf){
        serviceCliente.deletaCliente(cpf);
    }

    @GetMapping
    public List<Cliente> retornaClientes(){
        return serviceCliente.listaClientes();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> retornaDadosCliente(@PathVariable Integer cpf){
        Cliente cliente = serviceCliente.pegaCliente(cpf);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/{cpf}/nome")
    public String retornaNome(@PathVariable Integer cpf){
        return serviceCliente.pegaCliente(cpf).getNome();
    }

    @GetMapping("/{cpf}/dataNascimento")
    public Date retornaDataNascimento(@PathVariable Integer cpf){
        return serviceCliente.pegaCliente(cpf).getDataNascimento();
    }

    @GetMapping("/{cpf}/endereco")
    public String retornaEndereco(@PathVariable Integer cpf){
        return serviceCliente.pegaCliente(cpf).getEndereco();
    }

    @GetMapping("/{cpf}/email")
    public String retornaEmail(@PathVariable Integer cpf){
        return serviceCliente.pegaCliente(cpf).getEmail();
    }

    @GetMapping("/{cpf}/conta")
    public Conta requisitaConta(@PathVariable Integer cpf){
        return serviceCliente.pegaConta(cpf);
    }

    @GetMapping("/{cpf}/conta/saldo")
    public Double retornaSaldo(@PathVariable Integer cpf){
        return serviceCliente.pegaConta(cpf).getSaldo();
    }

    @PutMapping("/{cpf}")
    public void solicitaAtualizacaoCliente(@PathVariable Integer cpf, @RequestBody Cliente clienteAtual){
        serviceCliente.atualizaCliente(clienteAtual, cpf);
    }

    @PutMapping("/{cpf}/mudar-senha")
    public boolean solicitouModificarSenha(@PathVariable Integer cpf, @RequestBody ArrayList<String> senhas){

        String senhaAntiga = senhas.get(0);
        String novaSenha = senhas.get(1);

        String antiga = serviceCliente.encriptaSenha(senhaAntiga);

        if(serviceCliente.comparaSenha(cpf, antiga)){
            String nova = serviceCliente.encriptaSenha(novaSenha);
            serviceCliente.atualizaSenha(cpf, nova);
            return true;
        }
        return false;
    }

    @PutMapping("/{cpf}/saque")
    public boolean saque(@PathVariable Integer cpf, @RequestBody Double valor){
        return serviceCliente.atualizaSaldoConta(cpf, valor, "saque");
    }

    @PutMapping("/{cpf}/deposito")
    public boolean deposito(@PathVariable Integer cpf, @RequestBody Double valor){
        return serviceCliente.atualizaSaldoConta(cpf, valor, "deposito");
    }

    @PutMapping("/{cpf}/transferencia")
    public boolean transferencia(@PathVariable Integer cpf, @RequestBody ArrayList<String> dadosTransferencia){
        Double valor = Double.parseDouble(dadosTransferencia.get(0));
        Integer cpfDestinatario = Integer.parseInt(dadosTransferencia.get(1));

        if (serviceCliente.atualizaSaldoConta(cpf, valor, "saque") && serviceCliente.atualizaSaldoConta(cpfDestinatario, valor, "deposito")){
            return true;
        } return false;
    }


}