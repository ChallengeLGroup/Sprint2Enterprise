package org.example.models;

import jakarta.persistence.*;
import lombok.*;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @ToString
    @Table(name="endereco")
    public class Endereco {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long codigoEndereco;

        private String logradouro;
        private String numero;
        private String complemento;
        private String  bairro;
        private String  cidade;
        private String  estado;

        @Column(nullable = false)
        private String cep;


        @OneToOne(fetch = FetchType.LAZY)
        private Fornecedor fornecedores;

    }
