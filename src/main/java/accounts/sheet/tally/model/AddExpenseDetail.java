package accounts.sheet.tally.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddExpenseDetail {
    private ExpenseData data;
}
