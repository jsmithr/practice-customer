package pragma.customerservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pragma.customerservice.entity.IdentificationType;
import pragma.customerservice.repository.IdentificationTypeRepository;
import pragma.customerservice.service.impl.IdentificationTypeImpl;
import pragma.customerservice.service.srv.IdentificationTypeService;

import java.util.Optional;

@SpringBootTest
public class IdentificationTypeServiceMockTest {
    @Mock
    private IdentificationTypeRepository identificationTypeRepository;

    private IdentificationTypeService identificationTypeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        identificationTypeService = new IdentificationTypeImpl(identificationTypeRepository);
        IdentificationType identificationTypeBuild = IdentificationType.builder()
                .id(5)
                .abb("CC")
                .name("Cédula de Ciudadanía")
                .build();

        Mockito.when(identificationTypeRepository.findById(5))
                .thenReturn((Optional.of(identificationTypeBuild)));
    }

    @Test
    public void whenValidateGetID_ThenReturnIdentificationType() {
        IdentificationType found = identificationTypeService.get(5);
        Assertions.assertThat(found.getName()).isEqualTo("Cédula de Ciudadanía");
    }
}
