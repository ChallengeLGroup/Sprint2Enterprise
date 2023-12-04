package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Endereco;
import org.example.models.Usuario;
import org.example.repository.ContratoRepository;
import org.example.repository.SolicitacaoCompraRepository;
import org.example.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioRepositoryTest {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    public static UsuarioRepository usuarioRepository;


    @BeforeAll
    public static void setUp() {
        factory = Persistence.createEntityManagerFactory("oracle");
        manager = factory.createEntityManager();
        usuarioRepository = new UsuarioRepository(manager);

    }

    @Test
    public void createUsuario(){
        var usuario = new Usuario();
        usuario.setNome("user1");
        usuario.setSenha("123456");
        usuario.setEmail("user1@email.com");
        usuario.setSolicitacaoComprasSolicitante(null);
        usuario.setContratos(null);

        usuarioRepository.create(usuario);
        assertNotNull(usuario.getCodigoUsuario(), "Falha ao criar Usuario");

    }
    @Test
    @Order(2)
    public void updateUsuario() {
        var usuario = new Usuario();
        usuario.setNome("user1");
        usuario.setSenha("123456");
        usuario.setEmail("user1@email.com");
        usuario.setSolicitacaoComprasSolicitante(null);
        usuario.setContratos(null);

        usuarioRepository.create(usuario);
        assertNotNull(usuario.getCodigoUsuario(), "Falha ao criar Usuario");

        usuario.setNome("user2");
        usuario.setSenha("123456");
        usuario.setEmail("user1@email.com");
        usuario.setSolicitacaoComprasSolicitante(null);
        usuario.setContratos(null);

        usuarioRepository.update(usuario);

        var usuarioAtualizado = usuarioRepository.findById(usuario.getCodigoUsuario());
        assertEquals("user2", usuarioAtualizado.getNome(), "Falha ao atualizar Usuario");
    }

    @Test
    @Order(3)
    public void deleteUsuario() {
        var usuario = new Usuario();
        usuario.setNome("user1");
        usuario.setSenha("123456");
        usuario.setEmail("user1@email.com");
        usuario.setSolicitacaoComprasSolicitante(null);
        usuario.setContratos(null);

        usuarioRepository.create(usuario);
        assertNotNull(usuario.getCodigoUsuario(), "Falha ao criar Usuario");

        usuarioRepository.deleteById(usuario.getCodigoUsuario());
        var usuarioExcluido = usuarioRepository.findById(usuario.getCodigoUsuario());
        assertNull(usuarioExcluido, "Falha ao excluir Endereco");
    }


    @AfterAll
    public static void tearDown(){
        manager.close();
        factory.close();
    }

}
