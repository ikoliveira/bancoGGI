package com.ifpb.bancoggi.entidades;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"logado", "dataNascimento"})
public class Cliente {

    @Getter @Setter
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer cpf;

    @Getter @Setter
    private Date dataNascimento;

    @Getter @Setter
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