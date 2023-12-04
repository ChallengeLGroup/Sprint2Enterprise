package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "contratos")
public class Contrato {
     @Id
     @GeneratedValue
     private UUID contratoId ;

     @Column(nullable = false)
     private Date dataInicio;

     @Column(nullable = false)
     private Date dataTermino;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigoUsuario")
    private Usuario usuarios;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigoFornecedor")
    private Fornecedor fornecedores;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(
            name = "PedidoComprasContratos",
            joinColumns = @JoinColumn(name = "contratoId"),
            inverseJoinColumns = @JoinColumn(name = "codigoPedido")
    )
    private List<PedidoCompra> collaborations = new ArrayList<>();



}
