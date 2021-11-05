package com.ifpb.bancoggi.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"ativa"})
@Table(name = "tbl_contas", schema = "db_relationships")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id_conta", updatable = false, nullable = false)
    private Integer numeroConta;

    @Getter @Setter
    @Column(name = "senha_conta", updatable = true, nullable = false)
    private String senha;

    @Getter @Setter
    @Column(name = "saldo_conta", updatable = true, nullable = false)
    private Double saldo;

    @Getter @Setter
    @Column(name = "status_conta", updatable = true, nullable = false)
    private Boolean ativa;

    @Getter
    @Column(name = "idade_conta", updatable = true, nullable = false)
    private Date dataCriacao;

}
