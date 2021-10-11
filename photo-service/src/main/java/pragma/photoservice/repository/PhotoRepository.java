package pragma.photoservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pragma.photoservice.entity.Photo;

public interface PhotoRepository extends PagingAndSortingRepository<Photo, Integer>, MongoRepository<Photo, Integer> {
	public Photo getByIdCustomer(Long idCustomer);
}
