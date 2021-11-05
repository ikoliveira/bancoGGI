package com.ifpb.bancoggi.service;

import com.ifpb.bancoggi.entidades.Cliente;
import com.ifpb.bancoggi.repository.RepositoryCliente;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@NoArgsConstructor
public class ServiceCliente {

    ServiceConta serviceConta;
    RepositoryCliente clienteRepository;

    public void preparaRegistroCliente(String cpf, String nome, Date date) {
    }

    private Cliente recuperaCliente(Integer cpf) {
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

    public boolean comparaSenha(Integer cpfTratado, String antiga) {

        return false;

    }

    public void atualizaSenha(Integer cpfTratado, String nova) {



    }

    public void deletaCliente(Integer cpfTratado, String senha) {
    }
}
