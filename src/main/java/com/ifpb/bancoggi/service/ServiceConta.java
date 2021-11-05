package com.ifpb.bancoggi.service;

import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryConta;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@NoArgsConstructor
public class ServiceConta {

    private RepositoryConta repositoryConta;

    //eh private, so ta assim enquanto prepara registro nao funciona
    public Date geraDataCriacao(){
        Date data = new Date();
        return data;
    }

    //eh private, so ta assim enquanto prepara registro nao funciona
    public Conta localizaConta(Integer numConta){
        //o numero da conta vai ser gerado, nao precisa passar como parametro
        Conta conta = repositoryConta.recuperaConta(numConta);
        return conta;
    }

    public void preparaRegistroConta(String senha, Integer numConta){
        //geraNumConta() já gera em inteiro
        //o numero da conta vai ser gerado, nao precisa passar como parametro
        senha = encriptaSenha(senha);
        Date dataCriacao = geraDataCriacao();
    }

    //acho que nao precisa dessa
    public boolean analisaExistenciaConta(Integer numConta){
        //o numero da conta vai ser gerado, nao precisa passar como parametro
        return repositoryConta.verificaExistenciaConta(numConta);
    }

    public Double retornaSaldo(Integer numConta){
        //o numero da conta vai ser gerado, nao precisa passar como parametro
        Conta conta = localizaConta(numConta);
        return conta.getSaldo();
    }

    public void saque(Integer numConta, Double valor){
        repositoryConta.atualizaSaldoConta(numConta, valor, "saque");
    }

    public void deposito(Integer numConta, Double valor){
        repositoryConta.atualizaSaldoConta(numConta, valor, "deposito");

    }

    public boolean excluiConta(Integer numGerado, String senhaEncriptada){
        return repositoryConta.excluirConta(numGerado, senhaEncriptada);
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

    public boolean comparaSenha(String senha){
        senha = encriptaSenha(senha);
        return false;
    }

}
