package cz.codingmonkeys.ibs.repositories;

import cz.codingmonkeys.ibs.domain.transactions.Transaction;
import cz.codingmonkeys.ibs.dto.TransactionDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author rstefanca
 */

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	@Query(value = "SELECT T.ID, T.TRANSACTION_NAME as type, S.STATE_NAME FROM TRANSACTION  T JOIN TRANSACTION_STATE S ON T.CURRENT_STATE_ID = S.ID", nativeQuery = true)
	Collection<Object[]> findAllSimple();

}
