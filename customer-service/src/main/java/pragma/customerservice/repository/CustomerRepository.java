package pragma.customerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import pragma.customerservice.entity.City;
import pragma.customerservice.entity.Customer;
import pragma.customerservice.entity.IdentificationType;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer>, QuerydslPredicateExecutor<Customer> {

    public Page<Customer> findByIdentificationType (IdentificationType identificationType, Pageable pageable);
    public Customer findByIdentificationNumber (String identificationNumber);
    public Page<Customer> findByCity (City city, Pageable pageable);
    public Page<Customer> findByIdentificationNumberGreaterThanEqual (String identificationNumber, Pageable pageable);
    public Page<Customer> findByIdentificationNumberLessThanEqual (String identificationNumber, Pageable pageable);
    public Page<Customer> findByIdentificationNumberBetween (String max, String min, Pageable pageable);
}
