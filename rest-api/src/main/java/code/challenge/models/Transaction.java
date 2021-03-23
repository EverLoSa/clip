package code.challenge.models;


import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CLIP_TRANSACTION")
public class Transaction {

    @Id
    @Column(name = "TRANSACTION_ID")
    @SequenceGenerator(name = "TRANSACTION_SEQUENCE", sequenceName = "TRANSACTION_SEQUENCE_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACTION_SEQUENCE_ID")
    private Long transactionId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "AMOUNT")
    private Float amount;

    @Column(name = "CARD_DATA")
    private String cardData;

    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "disbursementId", nullable = true)
    private Disbursement disbursement;

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public Float getAmount() {
        return amount;
    }

    public String getCardData() {
        return cardData;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Disbursement getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(Disbursement disbursement) {
        this.disbursement = disbursement;
    }

    public Transaction(long userId, float amount, String cardData, Date transactionDate) {
        this.userId = userId;
        this.amount = amount;
        this.cardData = cardData;
        this.transactionDate = transactionDate;
        this.disbursement = null;
    }

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{ " +
                "transactionId=" + this.transactionId + "," +
                "userId=" + this.userId + "," +
                "amount=" + this.amount + "," +
                "cardData=" + this.cardData + "," +
                "transactionDate=" + this.transactionDate +
                "disbursement=" + ((this.disbursement == null) ? "N/A" : this.disbursement.getDisbursementId()) +
                "}";
    }
}
