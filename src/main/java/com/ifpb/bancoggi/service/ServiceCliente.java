package com.ifpb.bancoggi.service;

import com.ifpb.bancoggi.entidades.Cliente;
import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryCliente;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCliente {

    private ServiceConta serviceConta;
    private RepositoryCliente repositoryCliente;

    @Autowired
    public ServiceCliente(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    @Getter @Setter
    private Integer numConta;

    public void criandoCliente(Cliente cliente){
        String senha = cliente.getConta().getSenha();
        String senhaEncriptada = encriptaSenha(senha);

        Date data = geraDataCriacao();
        cliente.setLogado(true);
        cliente.getConta().setSaldo(0.0);
        cliente.getConta().setAtiva(true);
        cliente.getConta().setDataCriacao(data);
        cliente.getConta().setSenha(senhaEncriptada);

        repositoryCliente.save(cliente);
    }

    public void salvaCliente(Cliente cliente){
        repositoryCliente.save(cliente);
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
        return encriptaSenha(senha);
    }

    public boolean comparaSenha(Integer cpfTratado, String antiga) {

        return false;

    }

    public void atualizaSenha(Integer cpfTratado, String nova) {

    }

    public void deletaCliente(Integer cpfTratado, String senha) {
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

}
