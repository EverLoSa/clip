package code.challenge.services;


import code.challenge.dto.DisbursementDTO;
import code.challenge.models.Disbursement;
import code.challenge.models.Transaction;
import code.challenge.repositories.DisbursementRepository;
import code.challenge.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class DisbursementService {

    private final DisbursementRepository disbursementRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public DisbursementService(DisbursementRepository disbursementRepository, TransactionRepository transactionRepository) {
        this.disbursementRepository = disbursementRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<DisbursementDTO> getDisbursements() {
        List<DisbursementDTO> disbursementDTOList = new ArrayList<>();
        Iterable<Disbursement> disbursements = this.disbursementRepository.findAll();
        for (Disbursement disbursement : disbursements) {
            disbursementDTOList.add(
                    new DisbursementDTO(
                            disbursement.getDisbursementId(),
                            disbursement.getUserId(),
                            disbursement.getDisbursementAmount(),
                            disbursement.getDisbursementDate(),
                            disbursement.getTransactions()
                    )
            );
        }
        return disbursementDTOList;
    }

    public List<DisbursementDTO> getDisbursementsByUser(long user_id) {
        List<DisbursementDTO> disbursementDTOList = new ArrayList<>();
        Iterable<Disbursement> disbursements = this.disbursementRepository.findByUserId(user_id);
        for (Disbursement disbursement : disbursements) {
            disbursementDTOList.add(
                    new DisbursementDTO(
                            disbursement.getDisbursementId(),
                            disbursement.getUserId(),
                            disbursement.getDisbursementAmount(),
                            disbursement.getDisbursementDate(),
                            disbursement.getTransactions()
                    )
            );
        }
        return disbursementDTOList;
    }

    public List<DisbursementDTO> makeDisbursements() {

        Date disbursementDate = new Date(System.currentTimeMillis());

        logger.info("Getting available transactions for current disbursement");
        List<Transaction> transactions = this.transactionRepository.findTransactionsNotInDisbursement();

        logger.info("Grouping transactions by user");
        List<DisbursementDTO> disbursementDTOList = new ArrayList<>();
        transactions
                .stream()
                .collect(groupingBy(Transaction::getUserId))
                .forEach((k, v) -> {
                    DisbursementDTO disbursementDTO = new DisbursementDTO(new AbstractMap.SimpleEntry<>(k, v), disbursementDate);


                    Disbursement disbursement = new Disbursement(
                            disbursementDTO.getUserId(),
                            disbursementDTO.getDisbursementAmount(),
                            disbursementDate
                    );
                    long disbursementId = this.disbursementRepository.save(disbursement).getDisbursementId();
                    disbursementDTO.setDisbursementId(disbursementId);
                    disbursementDTOList.add(disbursementDTO);

                    for (Transaction transaction : v) {
                        transaction.setDisbursement(disbursement);
                        this.transactionRepository.save(transaction);
                    }
                });

        return disbursementDTOList;
    }


}
