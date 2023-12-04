package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pedidoCompra")
public class PedidoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoPedido;
    private Date dataEntregaPrevista;
    private Date dataPedido;

    @OneToMany(mappedBy = "pedidoCompra",fetch = FetchType.EAGER)
    private List<SolicitacaoCompra> codigoSolicitacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="codigoFornecedor")
    private Fornecedor fornecedores;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contratoId")
    private Contrato contratos;





}
