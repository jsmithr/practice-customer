package pragma.photoservice.exceptions;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private int code;
    private String message;
    private Date timestamp;
    private List<String> errors;

    ErrorResponse(String message){
        this.message = message;
    }
}
