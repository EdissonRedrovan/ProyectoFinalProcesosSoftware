package ec.edu.ups.controller;

import ec.edu.ups.dtos.CustomerDto;
import ec.edu.ups.model.Customer;
import ec.edu.ups.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author AdrianC47
 * @date 6/1/25
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class CustomerController {

    private final  CustomerService customerService;

    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @POST
    @Path("/list-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> listAllCustomers() {
        return customerService.listAll();
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveCustomer(CustomerDto customerDto) {
        try {
            validateCustomerInput(customerDto);

            Optional<Customer> existingCustomer = customerService.findByCedula(customerDto.getCedula());
            if (existingCustomer.isPresent()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Customer already exists")
                        .build();
            }

            Customer newCustomer = customerService.save(customerDto.toCustomer(customerDto));
            return Response.status(Response.Status.CREATED)
                    .entity(newCustomer)
                    .build();
        } catch (ValidationException e) {
            log.warn("Validation failed: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            log.error("Error saving customer", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }

    private void validateCustomerInput(CustomerDto customerDto) throws ValidationException {
        if (!customerService.validarCedula(customerDto.getCedula())) {
            throw new ValidationException("La cedula del cliente es invalida");
        }

        if (!customerService.isValidEmail(customerDto.getCorreo())) {
            throw new ValidationException("El correo del cliente es invalido");
        }
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(CustomerDto customerDto) {
        try {
            Optional<Customer> existingCustomer = customerService.findByCedula(customerDto.getCedula());
            if (existingCustomer.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Customer not found")
                        .build();
            }

            Customer customerToUpdate = existingCustomer.get();

            // Actualizar solo los campos permitidos
            customerToUpdate.setNombres(customerDto.getNombres());
            customerToUpdate.setApellidos(customerDto.getApellidos());
            customerToUpdate.setCorreo(customerDto.getCorreo());
            customerToUpdate.setTelefono(customerDto.getTelefono());
            customerToUpdate.setFechaNacimiento(customerDto.getFechaNacimiento());

            Customer updatedCustomer = customerService.save(customerToUpdate);
            return Response.ok(updatedCustomer).build();
        } catch (Exception e) {
            log.error("Error updating customer", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }



    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") Long id) {
        try {
            boolean deleted = customerService.deleteById(id);
            if (!deleted) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Customer not found")
                        .build();
            }
            return Response.ok("Customer successfully deleted").build();
        } catch (Exception e) {
            log.error("Error deleting customer", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }




}
