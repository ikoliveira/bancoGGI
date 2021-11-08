package com.ifpb.bancoggi.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"ativa"})
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Integer numeroConta;

    @Getter @Setter
    @Column(name = "senha", updatable = true, nullable = false)
    private String senha;

    @Getter @Setter
    @Column(name = "saldo", updatable = true, nullable = false)
    private Double saldo;

    @Getter @Setter
    @Column(name = "status", updatable = true, nullable = false)
    private Boolean ativa;

    @Getter @Setter
    @Column(name = "dataCriacao", updatable = true)
    private Date dataCriacao;

}
