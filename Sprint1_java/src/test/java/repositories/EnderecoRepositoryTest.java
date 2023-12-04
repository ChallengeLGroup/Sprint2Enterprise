package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Endereco;
import org.example.repository.EnderecoRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class EnderecoRepositoryTest {

    private static EntityManagerFactory factory;
    private static EntityManager manager;
    public static EnderecoRepository enderecoRepository;


    @BeforeAll
    public static void setUp() {
        factory = Persistence.createEntityManagerFactory("oracle");
        manager = factory.createEntityManager();
        enderecoRepository = new EnderecoRepository(manager);

    }
    @Test
    @Order(1)
    public void createEndereco(){
        var endereco = new Endereco();
        endereco.setLogradouro("Rua Consolação");
        endereco.setNumero("1234");
        endereco.setBairro("Consolação");
        endereco.setCidade("São paulo");
        endereco.setEstado("Sp");
        endereco.setCep("12345-678");
        endereco.setFornecedores(null);

        enderecoRepository.create(endereco);
        assertNotNull(endereco.getCodigoEndereco(), "Falha ao criar Endereco");
    }
    @Test
    @Order(2)
    public void updateEndereco() {
        var endereco = new Endereco();
        endereco.setLogradouro("Rua ABC");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Cidade");
        endereco.setEstado("Estado");
        endereco.setCep("12345-678");
        endereco.setFornecedores(null);

        enderecoRepository.create(endereco);
        assertNotNull(endereco.getCodigoEndereco(), "Falha ao criar Endereco");

        endereco.setLogradouro("Rua CBA");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Cidade");
        endereco.setEstado("Estado");
        endereco.setCep("12345-678");
        endereco.setFornecedores(null);
        enderecoRepository.update(endereco);

        var enderecoAtualizado = enderecoRepository.findById(endereco.getCodigoEndereco());
        assertEquals("Rua CBA", enderecoAtualizado.getLogradouro(), "Falha ao atualizar Endereco");
    }

    @Test
    @Order(3)
    public void deleteEndereco() {
        var endereco = new Endereco();
        endereco.setLogradouro("Rua ABC");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Cidade");
        endereco.setEstado("Estado");
        endereco.setCep("12345-678");
        endereco.setFornecedores(null);

        enderecoRepository.create(endereco);
        assertNotNull(endereco.getCodigoEndereco(), "Falha ao criar Endereco");

        enderecoRepository.deleteById(endereco.getCodigoEndereco());
        var enderecoExcluido = enderecoRepository.findById(endereco.getCodigoEndereco());
        assertNull(enderecoExcluido, "Falha ao excluir Endereco");
    }
@Test
@Order(4)
    public void getEnderecoByCep() {
    var endereco = new Endereco();
    endereco.setLogradouro("Rua Consolação");
    endereco.setNumero("1234");
    endereco.setBairro("Consolação");
    endereco.setCidade("São paulo");
    endereco.setEstado("Sp");
    endereco.setCep("12345-678");
    endereco.setFornecedores(null);

    enderecoRepository.create(endereco);

    var enderecoCep = enderecoRepository.findByCep("12345-678");
    assertNotNull(enderecoCep);
    assertFalse(enderecoCep.isEmpty());


}


    @AfterAll
    public static void tearDown(){
        manager.close();
        factory.close();
    }

}

