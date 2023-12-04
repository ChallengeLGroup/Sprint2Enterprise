package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="fornecedores")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoFornecedor;

    @Column(nullable = false)
    private String cnpj;

    private String nomeContato;
    private String telefone;
    private String email;


    @OneToMany(mappedBy = "fornecedores",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<PedidoCompra> pedidoComprasFornecedores;

    @OneToOne(mappedBy = "fornecedores", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Endereco enderecoFornecedor;

    @OneToMany(mappedBy = "fornecedores",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Contrato> contratos;






}