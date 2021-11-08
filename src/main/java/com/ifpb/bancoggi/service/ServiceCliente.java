package com.ifpb.bancoggi.service;

import com.ifpb.bancoggi.entidades.Cliente;
import com.ifpb.bancoggi.repository.RepositoryCliente;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ServiceCliente {


    ServiceConta serviceConta;
    RepositoryCliente repositoryCliente;

    @Autowired
    public ServiceCliente(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    public Cliente criandoCliente(Cliente cliente){
        Date data = new Date();
        cliente.getConta().setDataCriacao(data);
        Cliente clienteSalvo = repositoryCliente.save(cliente);
        return clienteSalvo;
    }
    public List<Cliente> exibeClientes(){
        return repositoryCliente.findAll();
    }

    public Cliente exibeCliente(Long numConta) throws ObjectNotFoundException {
        Optional<Cliente> cliente = repositoryCliente.findById(Math.toIntExact(numConta));
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "));
    }

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
