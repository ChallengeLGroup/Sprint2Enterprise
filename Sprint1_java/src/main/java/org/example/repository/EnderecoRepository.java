package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.models.Endereco;

import java.util.Optional;

public class EnderecoRepository extends JpaRepository<Endereco>{

    public EnderecoRepository(EntityManager entityManager) {

        super(entityManager, Endereco.class);
    }


    public Optional<Endereco> findByCep(String cep){
        var jpql = "SELECT a FROM Endereco a WHERE cep = :cep";
        try {
            return Optional.ofNullable(entityManager.createQuery(jpql, Endereco.class)
                    .setParameter("cep", cep)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
