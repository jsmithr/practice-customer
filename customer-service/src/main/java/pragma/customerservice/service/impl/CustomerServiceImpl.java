package pragma.customerservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pragma.customerservice.entity.Customer;
import pragma.customerservice.entity.QCustomer;
import pragma.customerservice.entity.query.QueryCustomer;
import pragma.customerservice.repository.CustomerRepository;
import pragma.customerservice.service.srv.CustomerService;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;

	@Override
	public Page<Customer> getAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public Customer get(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public Customer add(Customer customer) {
		customer.setStatus("CREATED");
		Customer customerBd = customerRepository.findByIdentificationNumber(customer.getIdentificationNumber());
		if (customerBd != null)
			return customerBd;
		return customerRepository.save(customer);
	}

	@Override
	public Customer update(Customer customer) {
		Customer customerBd = get(customer.getId());
		if (customerBd == null)
			return null;

		Customer customerBdIdentificationNumber = customerRepository.findByIdentificationNumber(customer.getIdentificationNumber());
		if (customerBdIdentificationNumber != null)
			if (customerBdIdentificationNumber.getId() != customer.getId())
				throw new DuplicateKeyException("Número de identificación duplicada");

		return customerRepository.save(validationFields(customer, customerBd));
	}

	@Override
	public Customer delete(Long id) {
		Customer customer = get(id);
		if (customer == null)
			return null;

		customer.setStatus("DELETED");
		return customerRepository.save(customer);
	}

	@Override
	public Page<Customer> getMultipleFields(QueryCustomer queryCustomer, Pageable pageable) {
		Predicate condition = condition(queryCustomer);
		return customerRepository.findAll(condition, pageable);
	}

	private Predicate condition(QueryCustomer queryCustomer) {
		var condition = new BooleanBuilder();
		QCustomer qcustomer = QCustomer.customer;

		if (queryCustomer.getIdentificationType() > 0)
			condition.and(qcustomer.identificationType.id.eq(queryCustomer.getIdentificationType()));

		if (queryCustomer.getCity() > 0)
			condition.and(qcustomer.city.id.eq(queryCustomer.getCity()));

		if (!queryCustomer.getIdentificationNumber().equals(""))
			condition.and(qcustomer.identificationNumber.contains(queryCustomer.getIdentificationNumber()));

		if (queryCustomer.getMinAge() > 0 && queryCustomer.getMaxAge() > 0)
			condition.and(qcustomer.age.between(queryCustomer.getMinAge(), queryCustomer.getMaxAge()));
		else {
			if (queryCustomer.getMinAge() > 0)
				condition.and(qcustomer.age.goe(queryCustomer.getMinAge()));

			if (queryCustomer.getMaxAge() > 0)
				condition.and(qcustomer.age.loe(queryCustomer.getMaxAge()));
		}

		return condition;
	}

	private Customer validationFields(Customer customer, Customer customerBd){
		if(customer.getIdentificationNumber() != null)
			customerBd.setIdentificationNumber(customer.getIdentificationNumber());

		if(customer.getName() != null)
			customerBd.setName(customer.getName());

		if(customer.getLastname() != null)
			customerBd.setLastname(customer.getLastname());

		if(customer.getCity() != null)
			customerBd.setCity(customer.getCity());

		if(customer.getIdentificationType() != null)
			customerBd.setIdentificationType(customer.getIdentificationType());

		if(customer.getAge() > 0)
			customerBd.setAge(customer.getAge());

		return customerBd;
	}
}
