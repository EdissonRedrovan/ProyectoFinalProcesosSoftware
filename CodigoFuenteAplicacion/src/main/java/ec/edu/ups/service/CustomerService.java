package ec.edu.ups.service;

import ec.edu.ups.model.Customer;
import ec.edu.ups.repository.CustomerRepository;
import ec.edu.ups.utils.Constants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author AdrianC47
 * @date 6/1/25
 */
@ApplicationScoped
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    // Expresión regular para validar correos electrónicos
    private static final Pattern EMAIL_PATTERN = Pattern.compile(Constants.EMAILREGEX);


    @Inject
    public CustomerService (CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public List<Customer> listAll() {
        return customerRepository.listAll();
    }

    public Optional<Customer> findByCedula(String cedula){
        return customerRepository.find("cedula", cedula).firstResultOptional();
    }

    public boolean deleteById(Long id) {
        return customerRepository.deleteById(id);
    }

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customerRepository.persist(customer);
        } else {
            customerRepository.getEntityManager().merge(customer);
        }
        return customer;
    }

    public static boolean validarCedula(String cedula) {
        char digito[] = cedula.toCharArray();
        int total = 0;
        for (int i = 0; i < digito.length - 1; i++) {
            int dato = Integer.parseInt(digito[i] + "");
            if (i % 2 == 0) {
                if (dato * 2 > 9) {
                    dato = (dato * 2) - 9;
                } else {
                    dato = dato * 2;
                }
            }
            total += dato;
        }
        int ultimo = Integer.parseInt(digito[digito.length - 1] + "");
        if (total % 10 == 0 && 0 == ultimo) {
            return true;
        } else {
            total = 10 - total % 10;
            if (total == ultimo) {
                return true;
            }
        }
        return false;
    }



    public boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
