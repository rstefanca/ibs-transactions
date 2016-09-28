package cz.codingmonkeys.ibs.domain.transactions;

import javax.persistence.Entity;

/**
 * @author Richard Stefanca
 */
@Entity
public class Completed extends TransactionState {

	protected Completed() {}

	Completed(TransactionState transactionState) {
		super(transactionState.getTransaction());
	}
}
