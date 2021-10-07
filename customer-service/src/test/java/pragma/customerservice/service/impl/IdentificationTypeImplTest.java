package pragma.customerservice.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Pageable;
import pragma.customerservice.entity.IdentificationType;
import pragma.customerservice.repository.IdentificationTypeRepository;
import pragma.customerservice.service.impl.IdentificationTypeImpl;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IdentificationTypeImplTest {

    @Mock
    private IdentificationTypeRepository identificationTypeRepository;

    @InjectMocks
    private IdentificationTypeImpl identificationTypeImpl;

    private IdentificationType identificationTypeBuild;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        identificationTypeImpl = new IdentificationTypeImpl(identificationTypeRepository);

        identificationTypeBuild = IdentificationType.builder()
                .id(5)
                .abb("CC")
                .name("Cédula de Ciudadanía")
                .build();

        Mockito.when(identificationTypeRepository.findAll())
                .thenReturn(Arrays.asList(identificationTypeBuild));

        Mockito.when(identificationTypeRepository.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(identificationTypeBuild));

        Mockito.when(identificationTypeRepository.findByAbb(ArgumentMatchers.any(String.class)))
                .thenReturn(identificationTypeBuild);

        Mockito.when(identificationTypeRepository.save(ArgumentMatchers.any(IdentificationType.class)))
                .thenReturn(identificationTypeBuild);
    }

    @Test
    public void getAll(Pageable pageable) {
        assertNotNull(identificationTypeImpl.getAll(pageable));
    }

    @Test
    public void get() {
        assertNotNull(identificationTypeImpl.get(5));
    }

    @Test
    void getByAbb() {
        Assertions.assertThat(identificationTypeBuild.getAbb()).isEqualTo("CC");
        assertNotNull(identificationTypeImpl.getByAbb("CC"));
    }

    @Test
    public void add() {
        assertNotNull(identificationTypeImpl.add(new IdentificationType()));
    }

    @Test
    public void update() {
        assertNotNull(identificationTypeImpl.update(new IdentificationType()));
    }

    @Test
    void delete() {
        assertNotNull(identificationTypeImpl.delete(5));
    }
}