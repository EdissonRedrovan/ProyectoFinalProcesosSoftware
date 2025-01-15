package ec.edu.ups.dtos;

import ec.edu.ups.model.Customer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author AdrianC47
 * @date 6/1/25
 */
@QuarkusTest
public class CustomerDtoTest {
    // Create CustomerDto with all fields populated and convert to Customer
    @Test
    public void test_convert_fully_populated_dto_to_customer() {
        Date birthDate = new Date();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setNombres("John");
        customerDto.setApellidos("Doe");
        customerDto.setCedula("1234567890");
        customerDto.setCorreo("john.doe@email.com");
        customerDto.setTelefono("0987654321");
        customerDto.setFechaNacimiento(birthDate);

        Customer customer = customerDto.toCustomer(customerDto);

        assertEquals(1L, customer.getId());
        assertEquals("John", customer.getNombres());
        assertEquals("Doe", customer.getApellidos());
        assertEquals("1234567890", customer.getCedula());
        assertEquals("john.doe@email.com", customer.getCorreo());
        assertEquals("0987654321", customer.getTelefono());
        assertEquals(birthDate, customer.getFechaNacimiento());
    }

    @Test
    public void testEqualsAndHashCode() {
        CustomerDto dto1 = new CustomerDto(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", new Date());
        CustomerDto dto2 = new CustomerDto(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", dto1.getFechaNacimiento());

        assertEquals(dto1, dto2, "CustomerDto objects with the same data should be equal");
        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes should match for equal objects");
    }

    @Test
    public void testToString() {
        CustomerDto dto = new CustomerDto(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", new Date());
        assertNotNull(dto.toString(), "toString() should return a non-null value");
    }

    @Test
    public void testToCustomer() {
        CustomerDto dto = new CustomerDto(1L, "John", "Doe", "0102030405", "john.doe@example.com", "0987654321", new Date());
        Customer customer = dto.toCustomer(dto);

        assertEquals(dto.getId(), customer.getId(), "ID should match");
        assertEquals(dto.getNombres(), customer.getNombres(), "Nombres should match");
        assertEquals(dto.getApellidos(), customer.getApellidos(), "Apellidos should match");
        assertEquals(dto.getCedula(), customer.getCedula(), "Cedula should match");
        assertEquals(dto.getCorreo(), customer.getCorreo(), "Correo should match");
        assertEquals(dto.getTelefono(), customer.getTelefono(), "Telefono should match");
        assertEquals(dto.getFechaNacimiento(), customer.getFechaNacimiento(), "FechaNacimiento should match");
    }
}
