package code.challenge.repositories;

import code.challenge.models.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findTransactionsByUserId(long userId);

    @Query(value = "SELECT " +
            "TRANSACTION_ID, " +
            "USER_ID, AMOUNT, " +
            "TRANSACTION_DATE,  " +
            "CARD_DATA,  " +
            "DISBURSEMENT_ID " +
            "FROM CLIP_TRANSACTION " +
            "WHERE DISBURSEMENT_ID IS NULL" ,
            nativeQuery = true
    )
    List<Transaction> findTransactionsNotInDisbursement();
}
