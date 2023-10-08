package accounts.sheet.tally.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiTransactionResponse {

    private String status;
    private HttpStatus errorCode;
    private String errorMessage;
    private Object body;
    private String message;

}
