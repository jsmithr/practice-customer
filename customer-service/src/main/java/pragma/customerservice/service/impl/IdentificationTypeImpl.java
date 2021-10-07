package pragma.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pragma.customerservice.entity.IdentificationType;
import pragma.customerservice.repository.IdentificationTypeRepository;
import pragma.customerservice.service.srv.IdentificationTypeService;

@Service
@RequiredArgsConstructor
public class IdentificationTypeImpl implements IdentificationTypeService {
    private final IdentificationTypeRepository identificationTypeRepository;

    @Override
    public Page<IdentificationType> getAll(Pageable pageable) {
        return identificationTypeRepository.findAll(pageable);
    }

    @Override
    public IdentificationType get(Integer id) {
        return identificationTypeRepository.findById(id).orElse(null);
    }

    @Override
    public IdentificationType getByAbb(String abb) {
        return identificationTypeRepository.findByAbb(abb);
    }

    @Override
    public IdentificationType add(IdentificationType identificationType) {
        IdentificationType identificationTypeBd = getByAbb(identificationType.getAbb());
        if (identificationTypeBd != null)
            return identificationTypeBd;

        identificationType.setStatus("CREATED");
        return identificationTypeRepository.save(identificationType);
    }

    @Override
    public IdentificationType update(IdentificationType identificationType) {
        IdentificationType identificationTypeBd = get(identificationType.getId());
        IdentificationType identificationTypeName = identificationTypeRepository.findByName(identificationType.getName());

        if (identificationTypeBd == null)
            return null;

        if(identificationTypeName != null)
            if(identificationTypeName.getId() != identificationType.getId())
                throw new DuplicateKeyException("Nombre ya existe");

        identificationTypeBd.setName(identificationType.getName());
        identificationTypeBd.setStatus("UPDATED");
        return identificationTypeRepository.save(identificationTypeBd);
    }

    @Override
    public IdentificationType delete(Integer id) {
        IdentificationType identificationTypeBd = get(id);
        if (identificationTypeBd == null)
            return null;

        identificationTypeBd.setStatus("DELETED");
        return identificationTypeRepository.save(identificationTypeBd);
    }
}
