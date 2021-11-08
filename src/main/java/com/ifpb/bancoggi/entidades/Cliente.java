package com.ifpb.bancoggi.entidades;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"logado", "dataNascimento"})
@Table(name = "tbl_clientes", schema = "db_relationships")
public class Cliente {

    @Id
    @Getter
    @Column(name = "cliente_id", updatable = false, nullable = false, unique = true)
    private Integer cpf;

    @Getter @Setter
    @Column(name = "cliente_name", updatable = false, nullable = false)
    private String nome;

    @Getter @Setter
    private Date dataNascimento;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_cliente_id")
    private Conta conta;

    @Getter @Setter private String endereco;

    @Getter @Setter
    private boolean logado;

    @Getter @Setter
    private String email;


    public boolean comparaEndeceo(String endereco){

        return this.endereco.equals(endereco);
    }

    public void logar(){
        setLogado(true);
    }

}