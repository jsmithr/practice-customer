package pragma.customerservice.service.srv;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pragma.customerservice.entity.Customer;
import pragma.customerservice.entity.query.QueryCustomer;

public interface CustomerService extends Service<Customer, Long> {
    public Page<Customer> getMultipleFields(QueryCustomer queryCustomer, Pageable pageable);
}
