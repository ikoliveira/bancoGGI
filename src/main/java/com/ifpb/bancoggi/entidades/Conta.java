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
    @Column(name = "numConta")
    private Integer numeroConta;

    @Getter @Setter
    @Column(name = "senha")
    private String senha;

    @Getter @Setter
    @Column(name = "saldo")
    private Double saldo;

    @Getter @Setter
    @Column(name = "status")
    private Boolean ativa;

    @Getter @Setter
    @Column(name = "dataCriacao")
    private Date dataCriacao;

}
