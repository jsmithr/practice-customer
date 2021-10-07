package pragma.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pragma.customerservice.entity.Customer;
import pragma.customerservice.repository.CustomerRepository;

@DataJpaTest
public class CustomerMockTest {

    @Autowired
    private CustomerRepository customerRepository;

    public void whenFindByIdentificationType_thenReturnList(){
        Customer customer = Customer.builder()
                .build();
    }
}
