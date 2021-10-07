package pragma.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceMockTest {
    /*@Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;*/

    @BeforeEach
    public void setup(){
        /*MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);*/
    }
}
