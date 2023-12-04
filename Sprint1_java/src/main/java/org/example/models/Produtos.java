package org.example.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="produtos")
public class Produtos {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String sku;
    private String nomeProduto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="codigoSolicitacao")
    private SolicitacaoCompra solicitacaoCompra;



}
