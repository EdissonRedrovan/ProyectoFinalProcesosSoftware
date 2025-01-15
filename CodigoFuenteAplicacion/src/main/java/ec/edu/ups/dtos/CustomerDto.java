package ec.edu.ups.dtos;

import ec.edu.ups.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author AdrianC47
 * @date 6/1/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String nombres;
    private String apellidos;
    private String cedula;
    private String correo;
    private String telefono;
    private Date fechaNacimiento;


    public Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setNombres(customerDto.getNombres());
        customer.setApellidos(customerDto.getApellidos());
        customer.setCedula(customerDto.getCedula());
        customer.setCorreo(customerDto.getCorreo());
        customer.setTelefono(customerDto.getTelefono());
        customer.setFechaNacimiento(customerDto.getFechaNacimiento());
        return customer;
    }
}