package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.models.Fornecedor;
import org.example.models.Produtos;

import java.util.Optional;

public class ProdutosRepository extends JpaRepository<Produtos>{
    public ProdutosRepository(EntityManager entityManager) {

        super(entityManager, Produtos.class);
    }
    public Optional<Produtos> findByNomeProdutos(String nomeProduto){
        var jpql = "SELECT a FROM Produtos a WHERE a.nomeProduto LIKE :nomeProduto";
        return Optional.ofNullable(entityManager.createQuery(jpql, Produtos.class)
                .setParameter("nomeProduto","%"+nomeProduto+"%")
                .getSingleResult());

    }
}
