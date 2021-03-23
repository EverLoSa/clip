package code.challenge.resources;

import javax.ws.rs.core.Response;

import code.challenge.dto.TransactionDTO;
import code.challenge.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/clip/transactions")
@Validated
public class TransactionResource {

    private final TransactionService transactionService;

    @Autowired
    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity getTransactionsByUser(@RequestParam(required = true, name = "user_id") long userId) {
        logger.info("Getting transactions for user {}", userId);
        try {
            List<TransactionDTO> transactions = this.transactionService.getTransactionsByUser(userId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("There was an error getting  transactions");
            return ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                    .body("There was an error getting  transactions: " + e.getMessage());

        }

    }

    @PostMapping
    public ResponseEntity makeTransaction(@RequestBody TransactionDTO transactionDTO) {

        try {
            logger.info("Sending Transactions");
            if (transactionDTO.getCardData() == null || transactionDTO.getCardData().isEmpty()) {

            }
            TransactionDTO transaction = transactionService.makeTransaction(
                    transactionDTO.getUserId(),
                    transactionDTO.getAmount(),
                    transactionDTO.getCardData()
            );
            logger.info("Transactions Saved successfully: {} ", transaction);
            return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).body(transaction);
        } catch (Exception e) {
            logger.error("There was an error processing the transaction");
            return ResponseEntity
                    .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                    .body("There was an error saving the transaction: " + e.getMessage());

        }
    }

}
