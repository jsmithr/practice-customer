package pragma.customerservice.service.srv;

import pragma.customerservice.entity.IdentificationType;

public interface IdentificationTypeService extends Service<IdentificationType, Integer> {
    public IdentificationType getByAbb(String abb);
}
