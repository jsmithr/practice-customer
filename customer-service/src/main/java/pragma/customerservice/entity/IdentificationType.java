package pragma.customerservice.entity;

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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
@EntityListeners(AuditingEntityListener.class)
public class IdentificationType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;

    @NotNull(message = "Abreviatura no debe ser nula")
    @NotEmpty(message = "Abreviatura no debe estar vacía")
    @Size(min = 2, max = 3)
    @Column(nullable = false, unique = true)
    private String abb;

    @NotNull(message = "Nombre no debe ser nulo")
    @NotEmpty(message = "Nombre de tipo de documento no debe estar vacío")
    @Column(nullable = false, unique = true)
    private String name;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;
}
