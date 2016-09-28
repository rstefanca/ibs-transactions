package cz.codingmonkeys.ibs.domain.transactions;

import javax.persistence.Entity;

/**
 * @author Richard Stefanca
 */
@Entity
public class Certified extends TransactionState {
	Certified(TransactionState transactionState) {
		super(transactionState.getTransaction());
	}

	@Override
	ChangeStateResult finish() {
		return changeState(new Completed(this));
	}
}
