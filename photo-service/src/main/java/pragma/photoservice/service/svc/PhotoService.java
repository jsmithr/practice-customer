package pragma.photoservice.service.svc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pragma.photoservice.entity.Photo;
import reactor.core.publisher.Flux;

public interface PhotoService extends Service<Photo, String>{
	public Flux<Photo> getByCustomer(Long idCustomer);
}
