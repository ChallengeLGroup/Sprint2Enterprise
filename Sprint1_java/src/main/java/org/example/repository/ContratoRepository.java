package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.models.Contrato;

public class ContratoRepository extends JpaRepository<Contrato>{


    public ContratoRepository(EntityManager entityManager) {

        super(entityManager, Contrato.class);
    }

}
