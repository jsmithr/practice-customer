package pragma.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pragma.customerservice.entity.IdentificationType;
import pragma.customerservice.exceptions.InvalidDataException;
import pragma.customerservice.exceptions.ValidationMethods;
import pragma.customerservice.service.srv.IdentificationTypeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/identificationType")
public class IdentificationTypeController extends ValidationMethods<IdentificationType> implements Controller<IdentificationType, Integer> {

	@Autowired
	private IdentificationTypeService identificationTypeService;

	@Override
	public ResponseEntity<IdentificationType> get(@PathVariable("id") Integer id) throws InvalidDataException {
		IdentificationType identificationType = identificationTypeService.get(id);
		return validateNull(identificationType);
	}

	@Override
	public ResponseEntity<IdentificationType> add(@Valid @RequestBody IdentificationType identificationType, BindingResult result) throws InvalidDataException {
		validateHasErrors(result);
		IdentificationType identificationTypeNew = identificationTypeService.add(identificationType);
		return validateNull(identificationTypeNew);
	}

	@Override
	public ResponseEntity<IdentificationType> update(@PathVariable("id") Integer id, @RequestBody IdentificationType identificationType) throws InvalidDataException {
		identificationType.setId(id);
		IdentificationType identificationTypeBd = identificationTypeService.update(identificationType);
		return validateNull(identificationTypeBd);
	}

	@Override
	public ResponseEntity<IdentificationType> delete(@PathVariable("id") Integer id) throws InvalidDataException {
		IdentificationType identificationTypeBd = identificationTypeService.delete(id);
		return validateNull(identificationTypeBd);
	}

	@GetMapping
	public ResponseEntity<Page<IdentificationType>> getAll(
			@RequestParam(name = "abb", required = false) String abb,
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy
	) throws InvalidDataException {
		Page<IdentificationType> identificationTypes = identificationTypeService.getAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending()));
		return ResponseEntity.ok(identificationTypes);
	}
}
