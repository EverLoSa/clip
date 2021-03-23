package code.challenge.repositories;

import code.challenge.models.Disbursement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisbursementRepository extends CrudRepository<Disbursement, Long> {

    List<Disbursement> findByUserId(long userId);

}
