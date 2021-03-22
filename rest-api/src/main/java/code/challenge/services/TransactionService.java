package code.challenge.services;


import code.challenge.models.Transaction;
import code.challenge.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<String> getTransactionsByUser(long userId) {
        Iterable<Transaction> transactionIterable = this.transactionRepository.findTransactionsByUserId(userId);
        List<String> transactions = new ArrayList<>();
        for (Transaction transaction : transactionIterable) {
            logger.info(transaction.toString());
            transactions.add(transaction.toString());
        }
        return transactions;
    }

    public Transaction makeTransaction(long userId, float amount, String cardData) {
        Date transactionDate = new Date(System.currentTimeMillis());
        Transaction transaction = new Transaction(userId, amount, cardData, transactionDate);
        return this.transactionRepository.save(transaction);
    }


}
