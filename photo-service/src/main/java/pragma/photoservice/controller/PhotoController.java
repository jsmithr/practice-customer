package pragma.photoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pragma.photoservice.entity.Photo;
import pragma.photoservice.exceptions.InvalidDataException;
import pragma.photoservice.exceptions.ValidationMethods;
import pragma.photoservice.service.impl.PhotoImpl;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/photo")
public class PhotoController extends ValidationMethods<Photo> {

	@Autowired
	private PhotoImpl photoService;

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Photo> get(@PathVariable("id") int id) throws Exception {

		return validateNull(new Photo());
	}

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Photo> add(@RequestParam("idCustomer") Long idCustomer,
									 @RequestParam("photo") MultipartFile photo) throws InvalidDataException, IOException {
		Photo newPhoto = photoService.add(idCustomer, photo);
		return validateNull(newPhoto);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Photo> update(@PathVariable("id") int id, @RequestBody Photo object) throws Exception {

		return validateNull(new Photo());
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Photo> delete(@PathVariable("id") int id) throws Exception {

		return validateNull(new Photo());
	}
}
