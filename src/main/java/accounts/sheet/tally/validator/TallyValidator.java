package accounts.sheet.tally.validator;

import accounts.sheet.tally.model.ExpenseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Slf4j
public class TallyValidator {


    public static void validateAddExpenseInput(ExpenseData data){
        log.info("TallyValidator --> Validating Input");
        Optional.ofNullable(data.getExpenseType()).filter(StringUtils::isNoneBlank)
                .orElseThrow(()-> new RuntimeException("Expense Type is required"));
        Optional.ofNullable(data.getAccount()).filter(StringUtils::isNoneBlank)
                .orElseThrow(()-> new RuntimeException("Account is required"));
        Optional.ofNullable(data.getDate()).filter(StringUtils::isNoneBlank)
                .orElseThrow(()-> new RuntimeException("Date is required"));
        Optional.ofNullable(data.getDescription()).filter(StringUtils::isNoneBlank)
                .orElseThrow(()-> new RuntimeException("Description is required"));
        Optional.ofNullable(data.getTransactionType()).filter(StringUtils::isNoneBlank)
                .orElseThrow(()-> new RuntimeException("Transaction Type is required"));
        Optional.of(data.getAmount()).filter(a->  a > 0)
                .orElseThrow(()-> new RuntimeException("Amount should be greater than 0"));
        log.info("TallyValidator --> validation successful");

    }
}
