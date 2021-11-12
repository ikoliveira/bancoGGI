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
    private HashMap<String, String> map = new HashMap<>();

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
        map.clear();
        map.put("nome", serviceCliente.getClienteLogado().getNome());
        return map;
    }

    @GetMapping("/data-nascimento") @ResponseBody
    public Map<String, Date> retornaDataNascimento(){
        HashMap<String, Date> mapa = new HashMap<>();
        mapa.put("dataNascimento", serviceCliente.getClienteLogado().getDataNascimento());
        return mapa;
    }

    @GetMapping("/endereco") @ResponseBody
    public Map<String, String> retornaEndereco(){
        map.clear();
        map.put("endereco", serviceCliente.getClienteLogado().getEndereco());
        return map;
    }

    @GetMapping("/email") @ResponseBody
    public Map<String, String> retornaEmail(){
        map.clear();
        map.put("email", serviceCliente.getClienteLogado().getEmail());
        return map;
    }

    @GetMapping("/conta") @ResponseBody
    public Conta requisitaConta(){
        return serviceCliente.getClienteLogado().getConta();
    }

    @GetMapping("/conta/saldo") @ResponseBody
    public Map<String, Double> retornaSaldo(){
        HashMap<String, Double> mapa = new HashMap<>();
        mapa.put("saldo", serviceCliente.getClienteLogado().getConta().getSaldo());
        return mapa;
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
    public boolean saque(@RequestBody HashMap<String, Double> saque){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        Double valor = saque.get("valor");
        return serviceCliente.atualizaSaldoConta(cpf, valor, "saque");
    }

    @PutMapping("/deposito")
    public boolean deposito(@RequestBody HashMap<String, Double> deposito){
        Integer cpf = serviceCliente.getClienteLogado().getCpf();
        Double valor = deposito.get("valor");
        return serviceCliente.atualizaSaldoConta(cpf, valor, "deposito");
    }

    @PutMapping("/transferencia")
    public boolean solicitaTransferencia(@RequestBody HashMap<String, String> dadosTransferencia){

        return serviceCliente.transferencia(dadosTransferencia);

    }


}
