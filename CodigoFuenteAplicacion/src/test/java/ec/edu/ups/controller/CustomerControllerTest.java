package ec.edu.ups.controller;

import ec.edu.ups.dtos.CustomerDto;
import ec.edu.ups.model.Customer;
import ec.edu.ups.service.CustomerService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author AdrianC47
 * @date 12/1/25
 */
@QuarkusTest
public class CustomerControllerTest {

    @InjectMock
    CustomerService customerService;

    @Test
    public void testListAllCustomers() {
        when(customerService.listAll()).thenReturn(Collections.emptyList());

        given()
                .contentType("application/json") // Asegúrate de incluir el encabezado
                .when().post("/customers/list-all")
                .then()
                .statusCode(200)
                .body(is("[]"));

        verify(customerService, times(1)).listAll();
    }



    @Test
    public void testUpdateCustomer() {
        CustomerDto customerDto = new CustomerDto(1L, "John", "Doe", "0102030405", "john.doe@example.com", "987654321", new Date());
        Customer customer = new Customer(1L, "John", "Doe", "0102030405", "john.doe@example.com", "123456789", new Date());

        when(customerService.findByCedula(customerDto.getCedula())).thenReturn(Optional.of(customer));
        when(customerService.save(Mockito.any(Customer.class))).thenReturn(customer);

        Response response = given()
                .contentType("application/json")
                .body(customerDto)
                .when().put("/customers/update")
                .then()
                .statusCode(200)
                .extract().response();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        verify(customerService, times(1)).save(Mockito.any(Customer.class));
    }

    @Test
    public void testDeleteCustomer() {
        Long customerId = 1L;

        when(customerService.deleteById(customerId)).thenReturn(true);

        Response response = given()
                .when().delete("/customers/delete/" + customerId)
                .then()
                .statusCode(200)
                .extract().response();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        verify(customerService, times(1)).deleteById(customerId);
    }


}
