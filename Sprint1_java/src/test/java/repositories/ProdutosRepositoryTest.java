package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Endereco;
import org.example.models.Fornecedor;
import org.example.models.Produtos;
import org.example.repository.ContratoRepository;
import org.example.repository.PedidoCompraRepository;
import org.example.repository.ProdutosRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutosRepositoryTest {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    public static ProdutosRepository produtosRepository;

    @BeforeAll
    public static void setUp() {
        factory = Persistence.createEntityManagerFactory("oracle");
        manager = factory.createEntityManager();
        produtosRepository = new ProdutosRepository(manager);

    }
    @Test
    @Order(1)
    public void createProduto(){
        var produto = new Produtos();
        produto.setNomeProduto("produto1");
        produto.setSolicitacaoCompra(null);


        produtosRepository.create(produto);
        assertNotNull(produto.getSku(),"Falha ao criar Produto");
    }
    @Test
    @Order(2)
    public void updateProduto() {
        var produto = new Produtos();
        produto.setNomeProduto("produto 1");
        produto.setSolicitacaoCompra(null);


        produtosRepository.create(produto);
        assertNotNull(produto.getSku(),"Falha ao criar Produto");


        produto.setNomeProduto("produto 2");
        produto.setSolicitacaoCompra(null);
        produtosRepository.update(produto);

        var produtoAtualizado = produtosRepository.findById(produto.getSku());
        assertEquals("produto 2", produtoAtualizado.getNomeProduto(), "Falha ao atualizar o produto");
    }

    @Test
    @Order(3)
    public void deleteProduto() {
        var produto = new Produtos();
        produto.setNomeProduto("produto 1");
        produto.setSolicitacaoCompra(null);


        produtosRepository.create(produto);
        assertNotNull(produto.getSku(),"Falha ao criar Produto");

        produtosRepository.deleteById(produto.getSku());
        var produtoExcluido = produtosRepository.findById(produto.getSku());
        assertNull(produtoExcluido, "Falha ao excluir Endereco");
    }
    @Test
    @Order(4)
    public void getProdutosByname(){
        var produto = new Produtos();
        produto.setNomeProduto("produto1");
        produto.setSolicitacaoCompra(null);


        produtosRepository.create(produto);
        assertNotNull(produto.getSku(),"Falha ao criar Produto");


        var produtoNome = produtosRepository.findByNomeProdutos("produto1");
        assertAll("Produto encontrado encontrado pelo Nome",
                (Executable) () -> assertTrue(produtoNome.isPresent(), "Produto não encontrado pelo Nome"),
                () -> {
                    var produtoNomeEncontrado = produtoNome.get();
                    assertEquals(produto.getNomeProduto(), produtoNomeEncontrado.getNomeProduto(), "Nome incorreto ");
                });

    }

    @AfterAll
    public static void tearDown(){
        manager.close();
        factory.close();
    }

}
