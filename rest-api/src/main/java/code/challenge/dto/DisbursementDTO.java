package code.challenge.dto;

import code.challenge.models.Transaction;

import java.sql.Date;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class DisbursementDTO {

    private long disbursementId;
    private long userId;
    private float disbursementAmount;
    private Date disbursementDate;
    private List<TransactionDTO> transactions;

    public long getDisbursementId() {
        return disbursementId;
    }

    public long getUserId() {
        return userId;
    }

    public float getDisbursementAmount() {
        return disbursementAmount;
    }

    public Date getDisbursementDate() {
        return disbursementDate;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setDisbursementId(long disbursementId) {
        this.disbursementId = disbursementId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDisbursementAmount(float disbursementAmount) {
        this.disbursementAmount = disbursementAmount;
    }

    public void setDisbursementDate(Date disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public DisbursementDTO(long disbursementId, long userId, float disbursementAmount, Date disbursementDate, List<Transaction> transactions) {
        this.disbursementId = disbursementId;
        this.userId = userId;
        this.disbursementAmount = disbursementAmount;
        this.disbursementDate = disbursementDate;
        this.transactions = convertTransactionToDTO(transactions);
    }

    public DisbursementDTO(AbstractMap.SimpleEntry<Long, List<Transaction>> disbursementInfo, Date date) {
        this.userId = disbursementInfo.getKey();
        this.disbursementDate = date;
        this.transactions = new ArrayList<>();
        disbursementInfo.getValue().forEach(t -> {
            this.transactions.add(new TransactionDTO(
                    t.getTransactionId(),
                    t.getUserId(),
                    t.getAmount(),
                    t.getCardData(),
                    t.getTransactionDate(),
                    t.getDisbursement())
            );
            this.disbursementAmount += t.getAmount();
        });
    }

    public DisbursementDTO() {
    }

    private List<TransactionDTO> convertTransactionToDTO(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDTOS.add(
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
        return transactionDTOS;
    }

}
