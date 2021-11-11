package com.ifpb.bancoggi.service;

import com.ifpb.bancoggi.entidades.Cliente;
import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryCliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class ServiceCliente {

    private RepositoryCliente repositoryCliente;

    @Getter @Setter
    private Cliente clienteLogado;

    @Autowired
    public ServiceCliente(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }


    private Date geraDataCriacao(){
        Date data = new Date();
        return data;
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

    private String encriptaSenha(String senha){
        return getHashMd5(senha);
    }

    private boolean comparaSenha(Integer cpf, String antiga) {
        return pegaCliente(cpf).getConta().getSenha().equals(antiga);

    }

    private boolean confirmarSenha(String senha, String senhaCornfirmada){
        return senha.equals(senhaCornfirmada);
    }

    private Integer geraNumConta(){

        int lenConta = 7;
        Random random = new Random();
        ArrayList<Integer> inter = new ArrayList<>();

       while (inter.size()< lenConta){
            Integer a = random.nextInt(9);

            if(inter.size() == 0 && a != 0){
                inter.add(a);
            } else if(inter.size() != 0){
                inter.add(a);
            }
        }

        String num = "";

        for (Integer integer : inter) {
            num += String.valueOf(integer);
        }

        return Integer.parseInt(num);

    }

    public void criandoCliente(Cliente cliente){
        String senha = cliente.getConta().getSenha();

        String senhaConfirmada = cliente.getConta().getSenhaConfirmada();

        String senhaEncriptada = encriptaSenha(senha);
        String senhaConfirmadaEncriptada = encriptaSenha(senhaConfirmada);

        if (confirmarSenha(senhaEncriptada, senhaConfirmadaEncriptada)){
            Integer numConta = geraNumConta();
            Date data = geraDataCriacao();

            cliente.setLogado(false);
            cliente.getConta().setNumeroConta(numConta);
            cliente.getConta().setSaldo(110.0);
            cliente.getConta().setAtiva(true);
            cliente.getConta().setDataCriacao(data);
            cliente.getConta().setSenha(senhaEncriptada);
            cliente.getConta().setSenhaConfirmada(null);

            repositoryCliente.save(cliente);
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

    public void atualizaSenha(Integer cpf, String nova) {
        Cliente cliente = pegaCliente(cpf);
        cliente.getConta().setSenha(nova);
        repositoryCliente.save(cliente);
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

    public boolean modificaSenha(Integer cpf, HashMap<String, String> senhas){
        String senhaAntiga = senhas.get("senhaAntiga");
        String novaSenha = senhas.get("novaSenha");
        String senhaConfirmada = senhas.get("senhaConfirmada");

        if (confirmarSenha(novaSenha, senhaConfirmada)){
            String antiga = encriptaSenha(senhaAntiga);

            if(comparaSenha(cpf, antiga)){
                String nova = encriptaSenha(novaSenha);
                atualizaSenha(cpf, nova);
                return true;
            }
        }
        return false;
    }

    public void logar(Integer cpf, String senha) {
        Cliente cliente = pegaCliente(cpf);
        String senhaEncriptada = encriptaSenha(senha);

        if (comparaSenha(cpf, senhaEncriptada)){
            cliente.setLogado(true);
            this.clienteLogado = cliente;

            repositoryCliente.save(cliente);
        }

    }
}
