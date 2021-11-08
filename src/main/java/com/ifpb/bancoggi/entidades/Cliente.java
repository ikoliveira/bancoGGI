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
    @Column(name = "cpf", updatable = false, nullable = false, unique = true)
    private Integer cpf;

    @Getter @Setter
    @Column(name = "nome", updatable = false, nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name = "dataNasc", updatable = false)
    private Date dataNascimento;

    @Getter @Setter
    @Column(name = "endereco", updatable = false, nullable = false)
    private String endereco;

    @Getter @Setter
    @Column(name = "logado", updatable = false, nullable = false)
    private boolean logado;

    @Getter @Setter
    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    @Getter @Setter
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_cliente_id")
    private Conta conta;

    public boolean comparaEndeceo(String endereco){

        return this.endereco.equals(endereco);
    }

    public void logar(){
        setLogado(true);
    }

}