package pragma.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pragma.customerservice.entity.City;
import pragma.customerservice.exceptions.InvalidDataException;
import pragma.customerservice.exceptions.ValidationMethods;
import pragma.customerservice.service.srv.CityService;

import javax.validation.Valid;

@RestController
@RequestMapping("/city")
public class CityController extends ValidationMethods<City> implements Controller<City, Integer> {

	@Autowired
	private CityService cityService;

	@Override
	public ResponseEntity<City> get(@PathVariable("id") Integer id) throws InvalidDataException {
		City city = cityService.get(id);
		return validateNull(city);
	}

	@Override
	public ResponseEntity<City> add(@Valid @RequestBody City city, BindingResult result) throws InvalidDataException {
		System.out.println("city = " + city + ", result = " + result);
		validateHasErrors(result);
		City cityNew = cityService.add(city);
		return ResponseEntity.ok(cityNew);
	}

	@Override
	public ResponseEntity<City> update(@PathVariable("id") Integer id, @RequestBody City city) throws InvalidDataException {
		city.setId(id);
		City cityBd = cityService.update(city);
		return validateNull(cityBd);
	}

	@Override
	public ResponseEntity<City> delete(@PathVariable("id") Integer id) throws InvalidDataException {
		City cityBd = cityService.delete(id);
		return validateNull(cityBd);
	}

	@GetMapping
	public ResponseEntity<Page<City>> getAll(
			@RequestParam(name = "abb", required = false) String abb,
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy
	) throws InvalidDataException {
		return ResponseEntity.ok(cityService.getAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy))));
	}
}
