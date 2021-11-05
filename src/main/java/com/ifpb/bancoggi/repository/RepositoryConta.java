package com.ifpb.bancoggi.repository;

import com.ifpb.bancoggi.entidades.Conta;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class RepositoryConta {

    private Map<Integer, Conta> contasCadastradas = new HashMap<Integer, Conta>();

    public void registraConta(String senhaEncriptada, Integer numGerado, Date dataCriacao){
        Double saldo = 0.0;
        Boolean ativa = true;

        Conta conta = new Conta(numGerado, senhaEncriptada, saldo, ativa, dataCriacao);
        contasCadastradas.put(numGerado, conta);
    }

    public Conta recuperaConta(Integer numConta){
        Conta conta = contasCadastradas.get(numConta);
        return conta;
    }

    public boolean verificaExistenciaConta(Integer numConta){
        return contasCadastradas.containsKey(numConta);
    }

    public boolean comparaSenha(Integer numGerado, String senhaEncriptada){
        Conta conta = recuperaConta(numGerado);
        return conta.getSenha().equals(senhaEncriptada);
    }

    public boolean excluirConta(Integer numGerado, String senhaEncriptada){
        if (comparaSenha(numGerado, senhaEncriptada)){
            contasCadastradas.remove(numGerado);
            return true;
        } return false;
    }
}
