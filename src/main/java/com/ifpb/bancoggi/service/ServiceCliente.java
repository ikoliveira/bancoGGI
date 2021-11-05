package com.ifpb.bancoggi.service;

import com.ifpb.bancoggi.entidades.Cliente;

import java.util.Date;

public class ServiceCliente {

    ServiceConta serviceConta;

    public void preparaRegistroCliente(String cpf, String nome, Date date) {
    }

    private Cliente recuperaCliente(Integer cpfConverter) {
        return null;
    }

    public String nomeCliente(Integer cpfConverter) {
        return "vai retornar o nome do cliente";
    }

    public String enderecoCliente(Integer cpfConvertido) {
        return "vai retornar o endereco";
    }

    public String dadosCliente(String cpf) {
        return "vai retornar o tostring";
    }

    public void atualizaEndereco(Integer cpfConvertido, String novoEndereco) {
    }

    public void atualizaNome(Integer cpfConvertido, String novoNome) {
    }

    public String solicitaEncriptacao(String senha) {
        return serviceConta.encriptaSenha(senha);
    }
}