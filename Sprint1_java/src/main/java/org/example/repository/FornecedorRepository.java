package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.models.Fornecedor;

import java.util.Optional;

public class FornecedorRepository extends JpaRepository<Fornecedor> {
    public FornecedorRepository(EntityManager entityManager) {
        super(entityManager, Fornecedor.class);
    }

    public Optional<Fornecedor> findByCnpj(String cnpj) {
        var jpql = "SELECT a FROM Fornecedor a WHERE a.cnpj LIKE :cnpj";
        return Optional.ofNullable(entityManager.createQuery(jpql, Fornecedor.class)
                .setParameter("cnpj", "%" + cnpj + "%")
                .getSingleResult());
    }
}
