package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Endereco;
import org.example.models.SolicitacaoCompra;
import org.example.repository.ContratoRepository;
import org.example.repository.ProdutosRepository;
import org.example.repository.SolicitacaoCompraRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SolicitacaoCompraRepositoryTest {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    public static SolicitacaoCompraRepository solicitacaoCompraRepository;

    @BeforeAll
    public static void setUp() {
        factory = Persistence.createEntityManagerFactory("oracle");
        manager = factory.createEntityManager();
        solicitacaoCompraRepository= new SolicitacaoCompraRepository(manager);

    }
    @Test
    @Order(1)
    public void createSolicitacaoCOmpra(){
        var solicitacaoCompra = new SolicitacaoCompra();
        solicitacaoCompra.setQuantidade(1);
        solicitacaoCompra.setValorUnitario(6);
        solicitacaoCompra.setStatus("aprovado");
        solicitacaoCompra.setMotivoRecusado("não");
        solicitacaoCompra.setDataSolicitacao(new Date(1670812800000L));
        solicitacaoCompra.setSku(null);
        solicitacaoCompra.setSolicitante(null);
        solicitacaoCompra.setPedidoCompra(null);

        solicitacaoCompraRepository.create(solicitacaoCompra);
        assertNotNull(solicitacaoCompra.getCodigoSolicitacao(), "Falha ao criar Solicitação compra");
    }

    @Test
    @Order(2)
    public void updateSolicitacaoCOmpra() {
        var solicitacaoCompra = new SolicitacaoCompra();
        solicitacaoCompra.setQuantidade(1);
        solicitacaoCompra.setValorUnitario(6);
        solicitacaoCompra.setStatus("aprovado");
        solicitacaoCompra.setMotivoRecusado("não");
        solicitacaoCompra.setDataSolicitacao(new Date(1670812800000L));
        solicitacaoCompra.setSku(null);
        solicitacaoCompra.setSolicitante(null);
        solicitacaoCompra.setPedidoCompra(null);

        solicitacaoCompraRepository.create(solicitacaoCompra);
        assertNotNull(solicitacaoCompra.getCodigoSolicitacao(), "Falha ao criar Solicitação compra");

        solicitacaoCompra.setQuantidade(10);
        solicitacaoCompra.setValorUnitario(6);
        solicitacaoCompra.setStatus("aprovado");
        solicitacaoCompra.setMotivoRecusado("não");
        solicitacaoCompra.setDataSolicitacao(new Date(1670812800000L));
        solicitacaoCompra.setSku(null);
        solicitacaoCompra.setSolicitante(null);
        solicitacaoCompra.setPedidoCompra(null);
        solicitacaoCompraRepository.update(solicitacaoCompra);

        var solicitacaoCompraAtualizada = solicitacaoCompraRepository.findById(solicitacaoCompra.getCodigoSolicitacao());
        assertEquals(10, solicitacaoCompraAtualizada.getQuantidade(), "Falha ao atualizar a Solicitação");
    }

    @Test
    @Order(3)
    public void deleteSolicitacaoCompra() {
        var solicitacaoCompra = new SolicitacaoCompra();
        solicitacaoCompra.setQuantidade(1);
        solicitacaoCompra.setValorUnitario(6);
        solicitacaoCompra.setStatus("aprovado");
        solicitacaoCompra.setMotivoRecusado("não");
        solicitacaoCompra.setDataSolicitacao(new Date(1670812800000L));
        solicitacaoCompra.setSku(null);
        solicitacaoCompra.setSolicitante(null);
        solicitacaoCompra.setPedidoCompra(null);

        solicitacaoCompraRepository.create(solicitacaoCompra);
        assertNotNull(solicitacaoCompra.getCodigoSolicitacao(), "Falha ao criar Solicitação compra");


        solicitacaoCompraRepository.deleteById(solicitacaoCompra.getCodigoSolicitacao());
        var solicitacaoCompraExcluida = solicitacaoCompraRepository.findById(solicitacaoCompra.getCodigoSolicitacao());
        assertNull(solicitacaoCompraExcluida, "Falha ao excluir Solicitação");
    }




    @AfterAll
    public static void tearDown(){
        manager.close();
        factory.close();
    }

}
