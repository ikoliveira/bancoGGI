package com.ifpb.bancoggi.controllers;

import com.ifpb.bancoggi.entidades.Cliente;

import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryCliente;
import com.ifpb.bancoggi.repository.RepositoryConta;
import com.ifpb.bancoggi.service.ServiceCliente;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public void atualizaEndereco(String cpf, String novoEndereco){
        Integer cpfConvertido = trataCPF(cpf);
        serviceCliente.atualizaEndereco(cpfConvertido, novoEndereco);
    }

    public boolean solicitouModificarSenha(String cpf, String senhaAntiga, String novaSenha){
        Integer cpfTratado = trataCPF(cpf);
        String antiga = serviceCliente.solicitaEncriptacao(senhaAntiga);
        if(serviceCliente.comparaSenha(cpfTratado, antiga)){
            String nova = serviceCliente.solicitaEncriptacao(novaSenha);
            serviceCliente.atualizaSenha(cpfTratado, nova);
            return true;
        }

        return false;
    }



    private Integer trataCPF(String cpf){
        int TAMANHOCPF = 11;
        if(cpf.length() != TAMANHOCPF){
            return 0;
        }
        return Integer.parseInt(cpf);
    }

}