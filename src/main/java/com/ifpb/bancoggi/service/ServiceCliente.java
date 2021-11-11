package com.ifpb.bancoggi.service;

import com.ifpb.bancoggi.entidades.Cliente;
import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryCliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCliente {

    private RepositoryCliente repositoryCliente;

    @Autowired
    public ServiceCliente(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    @Getter @Setter
    private Integer numConta;

    public void criandoCliente(Cliente cliente){
        String senha = cliente.getConta().getSenha();
        String senhaConfirmada = cliente.getConta().getSenhaConfirmada();

        String senhaEncriptada = encriptaSenha(senha);
        String senhaConfirmadaEncriptada = encriptaSenha(senhaConfirmada);

        if (confirmarSenha(senhaEncriptada, senhaConfirmadaEncriptada)){
            Date data = geraDataCriacao();
            cliente.setLogado(true);
            cliente.getConta().setSaldo(110.0);
            cliente.getConta().setAtiva(true);
            cliente.getConta().setDataCriacao(data);
            cliente.getConta().setSenha(senhaEncriptada);
            cliente.getConta().setSenhaConfirmada(null);

            repositoryCliente.save(cliente);
            //repositoryCliente.
        }
    }

    public void atualizaCliente(Cliente clienteAtual, Integer cpf){
        Cliente clienteAntigo = pegaCliente(cpf);

        clienteAntigo.setNome(clienteAtual.getNome());
        clienteAntigo.setDataNascimento(clienteAtual.getDataNascimento());
        clienteAntigo.setEndereco(clienteAtual.getEndereco());
        clienteAntigo.setEmail(clienteAtual.getEmail());

        repositoryCliente.save(clienteAntigo);
    }

    public List<Cliente> listaClientes(){
        return repositoryCliente.findAll();
    }

    public Cliente pegaCliente(Integer cpf){
        Optional<Cliente> cliente = repositoryCliente.findById(cpf);
        return cliente.get();
    }

    public void deletaCliente(Integer cpf){
        repositoryCliente.deleteById(cpf);
    }

    public Conta pegaConta(Integer cpf){
        Cliente cliente = pegaCliente(cpf);
        return cliente.getConta();
    }

    public boolean comparaSenha(Integer cpf, String antiga) {
        return pegaCliente(cpf).getConta().getSenha().equals(antiga);

    }

    public void atualizaSenha(Integer cpf, String nova) {
        Cliente cliente = pegaCliente(cpf);
        cliente.getConta().setSenha(nova);
        repositoryCliente.save(cliente);
    }

    public Date geraDataCriacao(){
        Date data = new Date();
        return data;
    }

    public String encriptaSenha(String senha){
        return getHashMd5(senha);
    }

    private String getHashMd5(String valor){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        BigInteger hash = new BigInteger(1, md.digest(valor.getBytes()));
        return hash.toString();

    }

    public boolean atualizaSaldoConta(Integer cpf, double valor, String tipoAtualizacao){
        Cliente cliente = pegaCliente(cpf);
        Conta conta = pegaConta(cpf);

        double novoSaldo;
        double saldo = conta.getSaldo();

        if(tipoAtualizacao.equals("saque")){
            if (saldo >= valor){
                novoSaldo = conta.getSaldo() - valor;
            } else{
                return false;
            }

        }else{
            novoSaldo = conta.getSaldo() + valor;
        }

        conta.setSaldo(novoSaldo);
        repositoryCliente.save(cliente);
        return true;
    }

    public boolean confirmarSenha(String senha, String senhaCornfirmada){
        return senha.equals(senhaCornfirmada);
    }

    public String dadosCliente(String cpf) {
        return "vai retornar o tostring";
    }

}
