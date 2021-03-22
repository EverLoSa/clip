package code.challenge.resources;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import code.challenge.models.Transaction;
import code.challenge.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/clip/transactions")
public class TransactionResource {

    private final TransactionService transactionService;

    @Autowired
    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<String>> getTransactionsByUser(@RequestParam(name = "user_id") long userId) {
        logger.info("Getting transactions for user {}", userId);
        try {
            List<String> transactions = this.transactionService.getTransactionsByUser(userId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("There was an error getting  transactions");
            return ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
        }

    }

    @PostMapping
    public ResponseEntity makeTransaction(
            @RequestParam(name = "user_id") long userId,
            @RequestParam(name = "amount") float amount,
            @RequestParam(name = "card_data") String cardData
    ) {
        logger.info("Sending Transactions");
        try {
            Transaction transaction = transactionService.makeTransaction(userId, amount, cardData);
            logger.info("Transactions Saved successfully: {} ", transaction.toString());
            return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).body(transaction.toString());
        } catch (Exception e) {
            logger.error("There was an error processing the transaction");
            return ResponseEntity
                    .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                    .body("There was an error saving the transaction " + e.getMessage());
        }
    }

}
