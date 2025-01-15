package ec.edu.ups.model;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import io.quarkus.test.junit.QuarkusTest;

/**
 * @author AdrianC47
 * @date 12/1/25
 */
@QuarkusTest
public class CustomerTest {

    @Test
    public void testEqualsAndHashCode() {
        Customer customer1 = new Customer(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", new Date());
        Customer customer2 = new Customer(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", customer1.getFechaNacimiento());

        assertEquals(customer1, customer2, "Customer objects with the same data should be equal");
        assertEquals(customer1.hashCode(), customer2.hashCode(), "Hash codes should match for equal objects");
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", new Date());
        assertNotNull(customer.toString(), "toString() should return a non-null value");
    }

    @Test
    public void testConstructor() {
        Customer customer = new Customer(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", new Date());

        assertEquals(1L, customer.getId(), "ID should match");
        assertEquals("John", customer.getNombres(), "Nombres should match");
        assertEquals("Doe", customer.getApellidos(), "Apellidos should match");
        assertEquals("0102030405", customer.getCedula(), "Cedula should match");
        assertEquals("john.doe@example.com", customer.getCorreo(), "Correo should match");
        assertEquals("0987654321", customer.getTelefono(), "Telefono should match");
        assertNotNull(customer.getFechaNacimiento(), "FechaNacimiento should not be null");
    }
}
