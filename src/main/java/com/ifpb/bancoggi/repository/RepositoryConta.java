package com.ifpb.bancoggi.repository;

import com.ifpb.bancoggi.entidades.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryConta extends JpaRepository <Conta, Long> {
}
