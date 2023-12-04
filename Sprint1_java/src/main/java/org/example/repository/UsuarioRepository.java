package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.models.Usuario;

public class UsuarioRepository extends JpaRepository<Usuario>{
    public UsuarioRepository(EntityManager entityManager ) {
        super(entityManager, Usuario.class);
    }
}
