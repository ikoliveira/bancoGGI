package com.ifpb.bancoggi.repository;

import com.ifpb.bancoggi.entidades.Cliente;
<<<<<<< HEAD
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCliente extends JpaRepository<Cliente, Long>{
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCliente extends JpaRepository<Cliente, Integer> {

>>>>>>> 2fd96764f027538ad053642722e3b905a716f77f
}
