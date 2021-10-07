package pragma.customerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface Controller <Entity, TypeId> {

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Entity> get(@PathVariable("id") TypeId id) throws Exception;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Entity> add(@Valid @RequestBody Entity object, BindingResult result) throws Exception;

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Entity> update(@PathVariable("id") TypeId id, @RequestBody Entity object) throws Exception;

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Entity> delete(@PathVariable("id") TypeId id) throws Exception;
}
