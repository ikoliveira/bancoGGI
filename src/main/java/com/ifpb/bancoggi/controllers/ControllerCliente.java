package com.ifpb.bancoggi.controllers;

import com.ifpb.bancoggi.entidades.Cliente;

import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryCliente;
import com.ifpb.bancoggi.service.ServiceCliente;
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

    @DeleteMapping
    public void solicitaExclusaoCliente(){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        serviceCliente.deletaCliente(cpf);
    }

    @GetMapping("/listaClientes")
    public List<Cliente> retornaClientes(){
        return serviceCliente.listaClientes();
    }

    @GetMapping
    public ResponseEntity<Cliente> retornaDadosCliente(){
        Cliente cliente = serviceCliente.getClienteLogado();
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping("/Login")
    public void solicitaLogin(@RequestBody HashMap<String, String> dadosLogar){
        Integer cpf = Integer.parseInt(dadosLogar.get("cpf"));
        String senha = dadosLogar.get("senha");

        serviceCliente.logar(cpf, senha);
    }

    @GetMapping("/nome") @ResponseBody
    public Map<String, String> retornaNome(){

        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        HashMap<String, String> map = new HashMap<>();
        map.put("nome", serviceCliente.pegaCliente(cpf).getNome());
        return map;
    }

    @GetMapping("/data-nascimento")
    public Date retornaDataNascimento(){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        return serviceCliente.pegaCliente(cpf).getDataNascimento();
    }

    @GetMapping("/endereco")
    public String retornaEndereco(){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        return serviceCliente.pegaCliente(cpf).getEndereco();
    }

    @GetMapping("/email")
    public String retornaEmail(){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        return serviceCliente.pegaCliente(cpf).getEmail();
    }

    @GetMapping("/conta")
    public Conta requisitaConta(){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        return serviceCliente.pegaConta(cpf);
    }

    @GetMapping("/conta/saldo")
    public Double retornaSaldo(){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        return serviceCliente.pegaConta(cpf).getSaldo();
    }

    @PutMapping
    public void solicitaAtualizacaoCliente(@RequestBody Cliente clienteAtual){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        serviceCliente.atualizaCliente(clienteAtual, cpf);
    }

    @PutMapping("/mudar-senha")
    public boolean solicitouModificarSenha(@RequestBody HashMap<String, String> senhas){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        return serviceCliente.modificaSenha(cpf, senhas);

    }

    @PutMapping("/saque")
    public boolean saque(@RequestBody Double valor){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        return serviceCliente.atualizaSaldoConta(cpf, valor, "saque");
    }

    @PutMapping("/deposito")
    public boolean deposito(@RequestBody Double valor){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        return serviceCliente.atualizaSaldoConta(cpf, valor, "deposito");
    }

    @PutMapping("/transferencia")
    public boolean transferencia(@RequestBody ArrayList<String> dadosTransferencia){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        Double valor = Double.parseDouble(dadosTransferencia.get(0));
        Integer cpfDestinatario = Integer.parseInt(dadosTransferencia.get(1));

        if (serviceCliente.atualizaSaldoConta(cpf, valor, "saque") && serviceCliente.atualizaSaldoConta(cpfDestinatario, valor, "deposito")){
            return true;
        } return false;
    }


}
