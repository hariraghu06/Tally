package accounts.sheet.tally.transformer;

import accounts.sheet.tally.model.ExpenseData;
import accounts.sheet.tally.model.ExpenseEntity;

public class TallyTransformer {

    public static ExpenseEntity toExpenseEntity(ExpenseData detail) {
        return ExpenseEntity.builder()
                .account(detail.getAccount())
                .amount(detail.getAmount())
                .date(detail.getDate())
                .expenseType(detail.getExpenseType())
                .description(detail.getDescription())
                .transactionType(detail.getTransactionType())
                .build();
    }
}
