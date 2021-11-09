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

    @GetMapping
    public List<Cliente> exibeClientes(){

        return serviceCliente.listaClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> exibeClientes(@PathVariable Integer id){
        Cliente cliente = serviceCliente.pegaCliente(id);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("Cliente/{cpf}")
    public Conta requisitaConta(@PathVariable Integer cpf){
        return serviceCliente.pegaConta(cpf);
    }

    public void requisitaCriacaoCliente(String cpf, String nome, int anoNascimento, int mesNascimento, int dataNascimento){
        Date date = new Date(anoNascimento, mesNascimento, dataNascimento);
        serviceCliente.preparaRegistroCliente(cpf, nome, date);
    }

    public String retornaNomeCliente(String cpf){
        Integer cpfConverter = Integer.parseInt(cpf);
        return serviceCliente.nomeCliente(cpfConverter);
    }

    public String retornaEndereco(String cpf){
        Integer cpfConvertido = trataCPF(cpf);
        return serviceCliente.enderecoCliente(cpfConvertido);
    }

    public String retornaDadosCliente(String cpf){
        Integer cpfConvertido = trataCPF(cpf);
        return serviceCliente.dadosCliente(cpf);
    }

    public void ajustaNomeCliente(String cpf, String novoNome){
        Integer cpfConvertido = trataCPF(cpf);
        serviceCliente.atualizaNome(cpfConvertido, novoNome);
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

    public void excluiCliente(String cpf, String senha){
        Integer cpfTratado = trataCPF(cpf);
        serviceCliente.deletaCliente(cpfTratado, senha);
    }

    private Integer trataCPF(String cpf){
        int TAMANHOCPF = 11;
        if(cpf.length() != TAMANHOCPF){
            return 0;
        }
        return Integer.parseInt(cpf);
    }

}