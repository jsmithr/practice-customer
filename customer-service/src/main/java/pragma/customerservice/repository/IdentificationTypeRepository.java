package pragma.customerservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pragma.customerservice.entity.IdentificationType;

public interface IdentificationTypeRepository extends PagingAndSortingRepository<IdentificationType, Integer> {

    public IdentificationType findByAbb (String abb);
    public IdentificationType findByName (String name);
}
