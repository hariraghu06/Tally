package accounts.sheet.tally.repository;

import accounts.sheet.tally.model.ExpenseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseInputRepository extends CrudRepository<ExpenseEntity, Long> {
    default void saveExpense(ExpenseEntity expenseEntity) {
        try {
            save(expenseEntity);
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while saving expense into DB due to " + e.getMessage());
        }

    }

    @Query(value = "select e.balance from expense_entity e where e.account = :account order by id desc limit 1;", nativeQuery = true)
    Double getLatestBalance(@Param("account") String account);

    @Query(value = "select * from expense_entity e where e.account = :account order by id desc limit :limit  ;" , nativeQuery = true )
    List<ExpenseEntity> fetchTransactions(String account, int limit);
}
