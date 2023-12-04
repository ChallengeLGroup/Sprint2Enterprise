package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.models.PedidoCompra;

public class PedidoCompraRepository  extends JpaRepository<PedidoCompra>{
    public PedidoCompraRepository(EntityManager entityManager) {
        super(entityManager, PedidoCompra.class);
    }
}
