package accounts.sheet.tally.model;

import lombok.Data;

@Data
public class ExpenseData {


    private String expenseType;
    private String date;
    private String account;
    private double amount;
    private String description;
    private String transactionType;
}
