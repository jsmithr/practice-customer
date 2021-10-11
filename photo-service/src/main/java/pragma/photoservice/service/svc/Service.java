package pragma.photoservice.service.svc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <Entity> valor del objeto a retornar
 */
public interface Service<Entity, TypeId> {
	public Page<Entity> getAll(Pageable pageable);

	public Entity get(TypeId id);

	public Entity add(Entity object);

	public Entity update(Entity object);

	public Entity delete(TypeId id);
}
