package ec.edu.ups.repository;

import ec.edu.ups.model.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author AdrianC47
 * @date 6/1/25
 */
@ApplicationScoped
public class CustomerRepository  implements PanacheRepository<Customer> {

}
