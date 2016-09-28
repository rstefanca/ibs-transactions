package cz.codingmonkeys.ibs.repositories;

import cz.codingmonkeys.ibs.domain.transactions.AbstractTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rstefanca
 */

@Repository
public interface TransactionRepository extends CrudRepository<AbstractTransaction, Long> {
}
