package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.PedidoCompra;
import org.example.repository.FornecedorRepository;
import org.example.repository.PedidoCompraRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoCompraRepositoryTest {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    public static PedidoCompraRepository pedidoCompraRepository;
    @BeforeAll
    public static void setUp() {
        factory = Persistence.createEntityManagerFactory("oracle");
        manager = factory.createEntityManager();
        pedidoCompraRepository = new PedidoCompraRepository(manager);

    }
    @Test
    @Order(1)
    public void CreatePedidoCompra(){
        var pedidoCompra = new PedidoCompra();

        pedidoCompra.setDataPedido(new Date(1670812800000L));
        pedidoCompra.setDataEntregaPrevista(new Date(1670812800000L));
        pedidoCompra.setCodigoSolicitacao(null);
        pedidoCompra.setFornecedores(null);
        pedidoCompra.setContratos(null);

        pedidoCompraRepository.create(pedidoCompra);
        assertNotNull(pedidoCompra.getCodigoPedido(), "Falha ao criar Pedido de compra");

    }

    @Test
    @Order(2)
    public void UpadatePedidoCompra(){
        var pedidoCompra = new PedidoCompra();

        pedidoCompra.setDataPedido(new Date(1670812800000L));
        pedidoCompra.setDataEntregaPrevista(new Date(1670812800000L));
        pedidoCompra.setCodigoSolicitacao(null);
        pedidoCompra.setFornecedores(null);
        pedidoCompra.setContratos(null);

        pedidoCompraRepository.create(pedidoCompra);
        assertNotNull(pedidoCompra.getCodigoPedido(), "Falha ao criar Pedido de compra");

        pedidoCompra.setDataPedido(new Date(1670812800000L));
        pedidoCompra.setDataEntregaPrevista(new Date(1670912800000L));
        pedidoCompra.setCodigoSolicitacao(null);
        pedidoCompra.setFornecedores(null);
        pedidoCompra.setContratos(null);

        var pedidoCompraAtualizado = pedidoCompraRepository.findById(pedidoCompra.getCodigoPedido());
        assertEquals(1670912800000L, pedidoCompraAtualizado.getDataEntregaPrevista().getTime() ,"Falha ao atualizar a data da Entrega prevista");

    }

    @Test
    @Order(3)
    public void deletePedidoCompra(){
        var pedidoCompra = new PedidoCompra();

        pedidoCompra.setDataPedido(new Date(1670812800000L));
        pedidoCompra.setDataEntregaPrevista(new Date(1670812800000L));
        pedidoCompra.setCodigoSolicitacao(null);
        pedidoCompra.setFornecedores(null);
        pedidoCompra.setContratos(null);

        pedidoCompraRepository.create(pedidoCompra);
        assertNotNull(pedidoCompra.getCodigoPedido(), "Falha ao criar Pedido de compra");



        pedidoCompraRepository.deleteById(pedidoCompra.getCodigoPedido());
        var pedidoCompraExcluido = pedidoCompraRepository.findById(pedidoCompra.getCodigoPedido());
        assertNull(pedidoCompraExcluido ,"Falha ao excluir o pedido Compra");


    }





    @AfterAll
    public static void tearDown(){
        manager.close();
        factory.close();
    }

}

