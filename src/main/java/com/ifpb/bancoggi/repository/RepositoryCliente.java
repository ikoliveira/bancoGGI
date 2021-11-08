package com.ifpb.bancoggi.repository;

import com.ifpb.bancoggi.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCliente extends JpaRepository<Cliente, Integer> {

}
