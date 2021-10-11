package pragma.photoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Document(collection = "photos")
public class Photo {
	@Id
	private String id;

	@Field("id_customer")
	@Indexed(unique = true)
	private Long idCustomer;
	private String photo;
	private String typeFile;
	private String status;

	@CreatedDate
	private String created;

	@LastModifiedDate
	private String updated;
}
