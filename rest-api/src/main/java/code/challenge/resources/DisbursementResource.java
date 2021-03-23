package code.challenge.resources;

import code.challenge.dto.DisbursementDTO;
import code.challenge.services.DisbursementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("clip/disbursement")
@Validated
public class DisbursementResource {

    private final DisbursementService disbursementService;

    @Autowired
    public DisbursementResource(DisbursementService disbursementService) {
        this.disbursementService = disbursementService;
    }

    @GetMapping
    public ResponseEntity getDisbursement() {
        logger.info("Getting Disbursements");
        try {
            List<DisbursementDTO> disbursements = this.disbursementService.getDisbursements();
            return ResponseEntity.ok(disbursements);
        } catch (Exception e) {
            logger.error("There was an error getting  Disbursements");
            return ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                    .body("There was an error getting  Disbursements: " + e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity getDisbursementByUser(@RequestParam(required = true, name = "user_id") long userId) {
        logger.info("Getting Disbursements");
        try {
            List<DisbursementDTO> disbursements = this.disbursementService.getDisbursementsByUser(userId);
            return ResponseEntity.ok(disbursements);
        } catch (Exception e) {
            logger.error("There was an error getting  Disbursements");
            return ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                    .body("There was an error getting  Disbursements: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity makeDisbursement() {

        try {
            logger.info("Creating Disbursement");
            List<DisbursementDTO> disbursementList = this.disbursementService.makeDisbursements();
            logger.info("Disbursement created successfully");
            return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).body(disbursementList);
        } catch (Exception e) {
            logger.error("There was an error creating the disbursement");
            return ResponseEntity
                    .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                    .body("There was an error creating the disbursement " + e.getMessage());
        }

    }


}

