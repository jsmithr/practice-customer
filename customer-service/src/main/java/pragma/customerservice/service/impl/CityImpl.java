package pragma.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pragma.customerservice.entity.City;
import pragma.customerservice.repository.CityRepository;
import pragma.customerservice.service.srv.CityService;

@Service
@RequiredArgsConstructor
public class CityImpl implements CityService {
    private final CityRepository CityRepository;

    @Override
    public Page<City> getAll(Pageable pageable) {
        return CityRepository.findAll(pageable);
    }

    @Override
    public City get(Integer id) {
        return CityRepository.findById(id).orElse(null);
    }

    @Override
    public City add(City City) {
        City.setStatus("CREATED");
        return CityRepository.save(City);
    }

    @Override
    public City update(City City) {
        City CityBd = get(City.getId());

        if (CityBd == null)
            return null;

        CityBd.setName(City.getName());
        CityBd.setStatus("UPDATED");
        return CityRepository.save(CityBd);
    }

    @Override
    public City delete(Integer id) {
        City CityBd = get(id);
        if (CityBd == null)
            return null;

        CityBd.setStatus("DELETED");
        return CityRepository.save(CityBd);
    }
}
