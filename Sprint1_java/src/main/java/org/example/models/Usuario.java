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
@Table(name= "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoUsuario;
    private String nome;
    private String senha;
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<SolicitacaoCompra> solicitacaoComprasSolicitante;


    @OneToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Contrato> contratos;


}
