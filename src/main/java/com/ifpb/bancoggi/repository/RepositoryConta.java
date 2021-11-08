package com.ifpb.bancoggi.repository;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.service.ServiceConta;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class RepositoryConta {

    private Map<Integer, Conta> contasCadastradas = new HashMap<Integer, Conta>();

    public void registraConta(Integer numGerado, String senhaEncriptada, Date dataCriacao){
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

    public boolean atualizaSaldoConta(Integer numConta, double valor, String tipoAtualizacao){
        Conta conta = recuperaConta(numConta);
        double novoSaldo;

        if(tipoAtualizacao.equals("saque")){
            novoSaldo = conta.getSaldo() - valor;
        }else{
            novoSaldo = conta.getSaldo() + valor;
        }
        conta.setSaldo(novoSaldo);
        return true;
    }
}
