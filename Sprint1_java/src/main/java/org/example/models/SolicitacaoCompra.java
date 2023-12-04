package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitacaoCompra")
public class SolicitacaoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigoSolicitacao;
    private Integer quantidade;
    private Integer valorUnitario;
    private String status;
    private String motivoRecusado;
    private Date dataSolicitacao;

    @OneToMany(mappedBy = "solicitacaoCompra",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Produtos> sku;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigoUsuario")
    private Usuario solicitante;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigoPedido")
    private PedidoCompra pedidoCompra;



}
