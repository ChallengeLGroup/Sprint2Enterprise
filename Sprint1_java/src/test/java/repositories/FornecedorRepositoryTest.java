package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Contrato;
import org.example.models.Endereco;
import org.example.models.Fornecedor;
import org.example.repository.ContratoRepository;
import org.example.repository.EnderecoRepository;
import org.example.repository.FornecedorRepository;
import org.example.repository.FornecedorRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FornecedorRepositoryTest {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    public static FornecedorRepository fornecedorRepository;

    @BeforeAll
    public static void setUp() {
        factory = Persistence.createEntityManagerFactory("oracle");
        manager = factory.createEntityManager();
        fornecedorRepository = new FornecedorRepository(manager);

    }
    @Test
    @Order(1)
    public void CreateFornecedor(){
        var fornecedor = new Fornecedor();

        fornecedor.setCnpj("123456789123");
        fornecedor.setEmail("fornecedorA@email.com");
        fornecedor.setNomeContato("fornecedor A");
        fornecedor.setTelefone("12345-6789");
        fornecedor.setPedidoComprasFornecedores(null);
        fornecedor.setEnderecoFornecedor(null);
        fornecedor.setContratos(null);

        fornecedorRepository.create(fornecedor);
        assertNotNull(fornecedor.getCodigoFornecedor(), "Falha ao criar Fornecedor");

    }

    @Test
    @Order(2)
    public void UpadateFornecedor(){
        var fornecedor = new Fornecedor();

        fornecedor.setCnpj("123456789123");
        fornecedor.setEmail("fornecedorA@email.com");
        fornecedor.setNomeContato("fornecedor A");
        fornecedor.setTelefone("12345-6789");
        fornecedor.setPedidoComprasFornecedores(null);
        fornecedor.setEnderecoFornecedor(null);
        fornecedor.setContratos(null);

        fornecedorRepository.create(fornecedor);
        assertNotNull(fornecedor.getCodigoFornecedor(), "Falha ao criar Fornecedor");

        fornecedor.setCnpj("123456789321");
        fornecedor.setEmail("fornecedorA@email.com");
        fornecedor.setNomeContato("fornecedor A");
        fornecedor.setTelefone("12345-6789");
        fornecedor.setPedidoComprasFornecedores(null);
        fornecedor.setEnderecoFornecedor(null);
        fornecedor.setContratos(null);

        fornecedorRepository.update(fornecedor);

        var fornecdedorAtualizado = fornecedorRepository.findById(fornecedor.getCodigoFornecedor());
        assertEquals("123456789321", fornecdedorAtualizado.getCnpj() ,"Falha ao atualizar o CNPJ");

    }

    @Test
    @Order(3)
    public void deleteFornecedor(){
        var fornecedor = new Fornecedor();

        fornecedor.setCnpj("123456789123");
        fornecedor.setEmail("fornecedorA@email.com");
        fornecedor.setNomeContato("fornecedor A");
        fornecedor.setTelefone("12345-6789");
        fornecedor.setPedidoComprasFornecedores(null);
        fornecedor.setEnderecoFornecedor(null);
        fornecedor.setContratos(null);

        fornecedorRepository.create(fornecedor);
        assertNotNull(fornecedor.getCodigoFornecedor(), "Falha ao criar Fornecedor");


        fornecedorRepository.deleteById(fornecedor.getCodigoFornecedor());
        var fornecedorExcluido = fornecedorRepository.findById(fornecedor.getCodigoFornecedor());
        assertNull(fornecedorExcluido, "Falha ao excluir fornecedor");


    }
    @Test
    @Order(4)
    public void getFornecedorByCNPJ(){
        var fornecedor = new Fornecedor();
        fornecedor.setCnpj("123456789123");
        fornecedor.setEmail("fornecedorA@email.com");
        fornecedor.setNomeContato("fornecedor A");
        fornecedor.setTelefone("12345-6789");
        fornecedor.setPedidoComprasFornecedores(null);
        fornecedor.setEnderecoFornecedor(null);
        fornecedor.setContratos(null);

        fornecedorRepository.create(fornecedor);
        assertNotNull(fornecedor.getCodigoFornecedor(), "Falha ao criar Fornecedor");

        var fornecedorCnpj = fornecedorRepository.findByCnpj("123456789123");

        assertNotNull(fornecedorCnpj);
        assertFalse(fornecedorCnpj.isEmpty());


    }



    @AfterAll
    public static void tearDown(){
        manager.close();
        factory.close();
    }

}
