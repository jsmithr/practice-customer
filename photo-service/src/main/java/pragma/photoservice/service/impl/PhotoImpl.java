package pragma.photoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pragma.photoservice.entity.Photo;
import pragma.photoservice.repository.PhotoRepository;
import pragma.photoservice.service.svc.PhotoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class PhotoImpl implements PhotoService {
	@Autowired
	private PhotoRepository photoRepository;

	@Override
	public Flux<Photo> getByCustomer(Long idCustomer) {
		return Flux.just(photoRepository.getByIdCustomer(idCustomer));
	}

	@Override
	public Page<Photo> getAll(Pageable pageable) {
		return photoRepository.findAll(pageable);
	}

	@Override
	public Photo get(String s) {
		return null;
	}

	@Override
	public Photo add(Photo photo) {
		/*Photo photoBd = photoRepository.getByIdCustomer(photo.getIdCustomer());*/
		return null; //photoRepository.save(photo);
	}

	public Photo add(Long idCustomer, MultipartFile photo) throws IOException {
		Flux<Photo> newPhoto = getByCustomer(idCustomer);
		System.out.println("Count = " + newPhoto.count());
		/*newPhoto.doOnNext(fp -> System.out.println(fp.filename())).then();*/
		byte[] fileContent = photo.getBytes();
		String encodedString = Base64.getEncoder().encodeToString(fileContent);

		return null; //photoRepository.save(photo);
	}

	@Override
	public Photo update(Photo photo) {
		return null;
	}

	@Override
	public Photo delete(String s) {
		return null;
	}
}
