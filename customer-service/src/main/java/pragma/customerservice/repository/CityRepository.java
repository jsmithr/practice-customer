package pragma.customerservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pragma.customerservice.entity.City;

public interface CityRepository extends PagingAndSortingRepository<City, Integer> {
}
