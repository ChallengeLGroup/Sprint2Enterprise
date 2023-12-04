package repositories;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Contrato;
import org.example.repository.ContratoRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static repositories.EnderecoRepositoryTest.enderecoRepository;

public class ContratoRepositoryTest {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    public static ContratoRepository contratoRepository;


    @BeforeAll
    public static void setUp() {
        factory = Persistence.createEntityManagerFactory("oracle");
        manager = factory.createEntityManager();
        contratoRepository = new ContratoRepository(manager);

}
@Test
@Order(1)
public void CreateContrato(){
        var contrato = new Contrato();

        contrato.setDataTermino(new Date(1670812800000L));
        contrato.setDataInicio(new Date(1670812800000L));
        contrato.setUsuarios(null);
        contrato.setFornecedores(null);
        contrato.setCollaborations(null);

    contratoRepository.create(contrato);
    assertNotNull(contrato.getDataInicio(), "Falha ao criar Contrato");

}

@Test
@Order(2)
public void UpadateContrato(){
    var contrato = new Contrato();

    contrato.setDataTermino(new Date(1670812800000L));
    contrato.setDataInicio(new Date(1670812800000L));
    contrato.setUsuarios(null);
    contrato.setFornecedores(null);
    contrato.setCollaborations(null);

    contratoRepository.create(contrato);
    assertNotNull(contrato.getDataInicio(), "Falha ao criar Contrato");

    contrato.setDataTermino(new Date(1670912800000L));
    contrato.setDataInicio(new Date(1670812800000L));
    contrato.setUsuarios(null);
    contrato.setFornecedores(null);
    contrato.setCollaborations(null);
    contratoRepository.update(contrato);

    var contratoAtualizado = contratoRepository.findById(contrato.getContratoId());
    assertEquals(1670912800000L, contratoAtualizado.getDataTermino().getTime() ,"Falha ao atualizar a data de termino");

}

@Test
@Order(3)
public void deleteContrato(){
    var contrato = new Contrato();
    contrato.setDataTermino(new Date(1670812800000L));
    contrato.setDataInicio(new Date(1670812800000L));
    contrato.setUsuarios(null);
    contrato.setFornecedores(null);
    contrato.setCollaborations(null);

    contratoRepository.create(contrato);
    assertNotNull(contrato.getDataInicio(), "Falha ao criar Contrato");


    contratoRepository.deleteById(contrato.getContratoId());
    var contratoExcluido = contratoRepository.findById(contrato.getContratoId());
    assertNull(contratoExcluido, "Falha ao excluir Contrato");


}

    @AfterAll
    public static void tearDown(){
        manager.close();
        factory.close();
    }

}
