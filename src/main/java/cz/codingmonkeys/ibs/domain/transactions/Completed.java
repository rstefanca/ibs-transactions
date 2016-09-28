package cz.codingmonkeys.ibs.domain.transactions;

import javax.persistence.Entity;

/**
 * @author Richard Stefanca
 */
@Entity
public class Completed extends TransactionState {
	Completed(TransactionState transactionState) {
		super(transactionState.getTransaction());
	}
}
