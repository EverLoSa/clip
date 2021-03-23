package code.challenge.services;


import code.challenge.dto.TransactionDTO;
import code.challenge.models.Transaction;
import code.challenge.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDTO> getTransactionsByUser(long userId) {
        // this could be a DTO.
        logger.info("Getting transactions from DB");
        Iterable<Transaction> transactionIterable = this.transactionRepository.findTransactionsByUserId(userId);
        List<TransactionDTO> transactions = new ArrayList<>();
        for (Transaction transaction : transactionIterable) {
            logger.info(transaction.toString());
            transactions.add(
                    new TransactionDTO(
                            transaction.getTransactionId(),
                            transaction.getUserId(),
                            transaction.getAmount(),
                            transaction.getCardData(),
                            transaction.getTransactionDate(),
                            transaction.getDisbursement()
                    )
            );
        }
        return transactions;
    }

    public TransactionDTO makeTransaction(long userId, float amount, String cardData) {
        Date transactionDate = new Date(System.currentTimeMillis());
        Transaction transaction = new Transaction(userId, amount, cardData, transactionDate);
        logger.info("Saving transaction into DB");
        this.transactionRepository.save(transaction);

        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getUserId(),
                transaction.getAmount(),
                transaction.getCardData(),
                transaction.getTransactionDate(),
                transaction.getDisbursement()
        );
    }


}
