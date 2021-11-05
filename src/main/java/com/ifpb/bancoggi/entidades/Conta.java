package com.ifpb.bancoggi.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"ativa"})
public class Conta {

    @Id
    @Getter
    private Integer numeroConta;

    @Getter @Setter
    private String senha;

    @Getter @Setter
    private Double saldo;

    @Getter @Setter
    private Boolean ativa;

    @Getter
    private Date dataCriacao;

}
