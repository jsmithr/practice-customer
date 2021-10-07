package pragma.customerservice.entity.query;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Esta clase es utilizada para mapear los datos obtenidos del cliente para realizar consultas.
 */

@Getter @Setter
@Builder
public class QueryCustomer implements Serializable {
	private Integer identificationType;
	private Integer city;
	private String identificationNumber;
	private Integer minAge;
	private Integer maxAge;
}
