package code.challenge.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "CLIP_DISBURSEMENT")
public class Disbursement {

    @Id
    @Column(name = "DISBURSEMENT_ID")
    @SequenceGenerator(name = "DISBURSEMENT_SEQUENCE", sequenceName = "DISBURSEMENT_SEQUENCE_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "DISBURSEMENT_SEQUENCE_ID")
    private long disbursementId;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "DISBURSEMENT_AMOUNT")
    private float disbursementAmount;

    @Column(name = "DISBURSEMENT_DATE")
    private Date disbursementDate;

    @OneToMany(mappedBy = "disbursement")
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

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

    public Disbursement(){}

    public Disbursement(long userId, float disbursementAmount,Date disbursementDate){
     this.userId = userId;
     this.disbursementAmount = disbursementAmount;
     this.disbursementDate = disbursementDate;
    }

    @Override
    public String toString(){
        return "Disbursment{ " +
                "disbursementId=" + this.disbursementId + "," +
                "userId=" + this.userId + "," +
                "disbursementAmount=" + this.disbursementAmount + "," +
                "disbursementDate=" + this.disbursementDate +
                "}";
    }

}
