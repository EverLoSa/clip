package code.challenge.dto;

import code.challenge.models.Disbursement;

import java.util.Date;

public class TransactionDTO {

    private Long transactionId;
    private Long userId;
    private Float amount;
    private String cardData;
    private Date transactionDate;

    private Long disbursementID = null;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCardData() {
        return cardData;
    }

    public void setCardData(String cardData) {
        this.cardData = cardData;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getDisbursement() {
        return disbursementID;
    }

    public void setDisbursement(long disbursementID) {
        this.disbursementID = disbursementID;
    }

    public TransactionDTO(Long transactionId, Long userId, Float amount, String cardData, Date transactionDate, Disbursement disbursement) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.cardData = cardData;
        this.transactionDate = transactionDate;
        if (disbursement != null) {
            this.disbursementID = disbursement.getDisbursementId();
        }
    }

    public TransactionDTO() {
    }
}
