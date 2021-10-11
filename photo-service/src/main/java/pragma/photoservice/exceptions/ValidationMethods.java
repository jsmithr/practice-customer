package pragma.photoservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 * Métodos de validación para las excepciones
 */
public class ValidationMethods<Entity> {
	public void validateHasErrors(BindingResult result) throws InvalidDataException {
		System.out.println("result = " + result);
		if (result.hasErrors())
			throw new InvalidDataException(result);
	}

	public ResponseEntity<Entity> validateNull(Entity model) {
		return model == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(model);
	}
}
