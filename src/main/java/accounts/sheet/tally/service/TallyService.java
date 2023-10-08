package accounts.sheet.tally.service;

import accounts.sheet.tally.model.AccountEntity;
import accounts.sheet.tally.model.ApiTransactionResponse;
import accounts.sheet.tally.model.ExpenseData;
import accounts.sheet.tally.model.ExpenseEntity;
import accounts.sheet.tally.repository.ExpenseInputRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static accounts.sheet.tally.TallyConstants.FAILED;
import static accounts.sheet.tally.TallyConstants.SUCCESS;
import static accounts.sheet.tally.transformer.TallyTransformer.toExpenseEntity;
import static accounts.sheet.tally.validator.TallyValidator.validateAddExpenseInput;

@Service
@Slf4j
public class TallyService {

    @Autowired
    ExpenseInputRepository repository;

    @Autowired
    EntityComponent entityComponent;


    public ApiTransactionResponse addExpense(ExpenseData expenseData) {
        log.info("TallyService --> addExpense with {}", expenseData.toString());
        try {
            validateAddExpenseInput(expenseData);
            ExpenseEntity expenseEntity = toExpenseEntity(expenseData);
            updateBalance(expenseEntity);
            repository.saveExpense(expenseEntity);
            return ApiTransactionResponse.builder()
                    .status(SUCCESS)
                    .body(expenseData)
                    .message("Expense added into account")
                    .build();
        } catch (RuntimeException exception) {
            log.error("error : {}", exception.getMessage());
            return ApiTransactionResponse.builder()
                    .status(FAILED)
                    .errorMessage(exception.getMessage())
                    .errorCode(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public void updateBalance(ExpenseEntity expenseEntity) {
        log.info("TallyService --> Updating balance ");
        Double currentBalance = repository.getLatestBalance(expenseEntity.getAccount());
        switch (expenseEntity.getTransactionType()) {
            case "Credit" -> Optional.of(expenseEntity.getAmount()).filter(a -> currentBalance > a)
                    .ifPresentOrElse(a -> expenseEntity.setBalance(currentBalance - a), () -> {
                        throw new RuntimeException("Amount is higher than balance ");
                    });
            case "Debit" -> expenseEntity.setBalance(currentBalance + expenseEntity.getAmount());
            default -> throw new RuntimeException("Invalid transaction type");
        }

    }

    public ApiTransactionResponse fetchTransactionDetails(String account) {
        log.info("TallyService --> Fetching transaction data for {}", account);
        List<ExpenseEntity> entityList = repository.fetchTransactions(account, 20);
        return ApiTransactionResponse.builder().body(entityList).status(SUCCESS).build();
    }

    public ApiTransactionResponse fetchAccountList() {
        log.info("TallyService --> Fetching account list");
        try {
            List<AccountEntity> accountEntities = entityComponent.findAllEntities();
            return ApiTransactionResponse.builder()
                    .body(accountEntities)
                    .status(SUCCESS)
                    .build();
        } catch (RuntimeException exception) {
            return ApiTransactionResponse.builder()
                    .status(FAILED)
                    .errorCode(HttpStatus.BAD_REQUEST)
                    .errorMessage(exception.getMessage())
                    .build();
        }
    }
}
