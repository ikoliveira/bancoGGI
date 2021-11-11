package com.ifpb.bancoggi.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"logado", "dataNascimento"})
public class Cliente {

    @Id
    @Getter
    @Column(name = "cpf")
    private Integer cpf;

    @Getter @Setter
    @Column(name = "nome")
    private String nome;

    @Getter @Setter
    @Column(name = "dataNasc")
    private Date dataNascimento;

    @Getter @Setter
    @Column(name = "endereco")
    private String endereco;

    @Getter @Setter
    @Column(name = "logado")
    private boolean logado;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_cliente_id")
    private Conta conta;

}