package ec.edu.ups.service;

import ec.edu.ups.model.Customer;
import ec.edu.ups.repository.CustomerRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author AdrianC47
 * @date 12/1/25
 */
@QuarkusTest
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Customer customer1 = new Customer(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", null);
        Customer customer2 = new Customer(2L, "Jane", "Smith", "0203040506", "jane.smith@example.com", "0976543210", null);

        when(customerRepository.listAll()).thenReturn(List.of(customer1, customer2));

        List<Customer> customers = customerService.listAll();

        assertNotNull(customers);
        assertEquals(2, customers.size());
        verify(customerRepository, times(1)).listAll();
    }



    @Test
    void testDeleteById() {
        Long id = 1L;

        when(customerRepository.deleteById(id)).thenReturn(true);

        boolean result = customerService.deleteById(id);

        assertTrue(result);
        verify(customerRepository, times(1)).deleteById(id);
    }

    @Test
    void testSaveNewCustomer() {
        Customer customer = new Customer(null, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", null);

        customerService.save(customer);

        verify(customerRepository, times(1)).persist(customer);
    }

    @Test
    void testSaveExistingCustomer() {
        // Crear un cliente para simular el caso de prueba
        Customer customer = new Customer(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", null);

        // Configurar el mock para que el EntityManager no sea null
        when(customerRepository.getEntityManager()).thenReturn(entityManager);

         customerService.save(customer);

         verify(entityManager, times(1)).merge(customer);
    }

    @Test
    void testValidarCedulaValid() {
        String validCedula = "1234567848";

        assertTrue(CustomerService.validarCedula(validCedula));
    }

    @Test
    void testValidarCedulaInvalid() {
        String invalidCedula = "1234567890";

        assertFalse(CustomerService.validarCedula(invalidCedula));
    }

    @Test
    void testIsValidEmailValid() {
        String validEmail = "john.doe@example.com";

        assertTrue(customerService.isValidEmail(validEmail));
    }

    @Test
    void testIsValidEmailInvalid() {
        String invalidEmail = "john.doe@invalid";

        assertFalse(customerService.isValidEmail(invalidEmail));
    }

    // Helper method to mock PanacheQuery
    private <T> PanacheQuery<Customer> mockQueryResult(T... items) {
        PanacheQuery<T> query = mock(PanacheQuery.class);
        when(query.firstResultOptional()).thenReturn(Optional.ofNullable(items.length > 0 ? items[0] : null));
        return (PanacheQuery<Customer>) List.of(items);
    }
}
