package pragma.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pragma.customerservice.entity.Customer;
import pragma.customerservice.entity.query.QueryCustomer;
import pragma.customerservice.exceptions.InvalidDataException;
import pragma.customerservice.exceptions.ValidationMethods;
import pragma.customerservice.service.impl.CustomerServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController extends ValidationMethods<Customer> implements Controller<Customer, Long> {

	@Autowired
	private CustomerServiceImpl customerService;

	@Override
	public ResponseEntity<Customer> get(@PathVariable(name = "id") Long id) throws InvalidDataException {
		Customer customer = customerService.get(id);
		return validateNull(customer);
	}

	@Override
	public ResponseEntity<Customer> add(@Valid @RequestBody Customer customer, BindingResult result) throws InvalidDataException {
		validateHasErrors(result);
		Customer customerNew = customerService.add(customer);
		return validateNull(customerNew);
	}

	@Override
	public ResponseEntity<Customer> update(@PathVariable("id") Long id, @RequestBody Customer customer) throws InvalidDataException {
		customer.setId(id);
		Customer customerBd = customerService.update(customer);
		return validateNull(customerBd);
	}

	@Override
	public ResponseEntity<Customer> delete(@PathVariable("id") Long id) throws InvalidDataException {
		Customer customerDelete = customerService.delete(id);
		return validateNull(customerDelete);
	}


	@GetMapping
	public ResponseEntity<Page<Customer>> getAll(
			@RequestParam(required = false, defaultValue = "0") Integer identificationType,
			@RequestParam(required = false, defaultValue = "") String identificationNumber,
			@RequestParam(required = false, defaultValue = "0") Integer city,
			@RequestParam(required = false, defaultValue = "0") Integer minAge,
			@RequestParam(required = false, defaultValue = "0") Integer maxAge,
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy
	) throws InvalidDataException {
		QueryCustomer customer = QueryCustomer.builder()
				.identificationType(identificationType)
				.identificationNumber(identificationNumber)
				.city(city)
				.minAge(minAge)
				.maxAge(maxAge)
				.build();
		Page<Customer> customerPage = customerService.getMultipleFields(customer, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
		return ResponseEntity.ok(customerPage);
	}
}
