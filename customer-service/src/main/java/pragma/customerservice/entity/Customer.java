package pragma.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
@EntityListeners(AuditingEntityListener.class)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Nombre no debe ser vacío")
    private String name;

    @NotEmpty(message = "Apellido no debe ser vacío")
    private String lastname;

    @Positive(message = "Número de Identificación debe ser mayor que 0")
    private String identificationNumber;

    @NotNull
    @Positive(message = "La edad debe ser mayor a 0")
    @Column(nullable = false)
    private int age;
    private String status;

    @NotNull(message = "Tipo identificación no debe ser nula")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_identificationType")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private IdentificationType identificationType;

    @NotNull(message = "Ciudad no debe ser nula")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private City city;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;
}
