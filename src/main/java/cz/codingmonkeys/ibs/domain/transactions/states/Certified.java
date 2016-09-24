package cz.codingmonkeys.ibs.domain.transactions.states;

import cz.codingmonkeys.ibs.domain.transactions.AbstractTransaction;
import cz.codingmonkeys.ibs.domain.transactions.TransactionState;

/**
 * @author Richard Stefanca
 */
public class Certified extends TransactionState {
	public Certified(AbstractTransaction abstractTransaction) {
		super(abstractTransaction);
	}

	@Override
	public void finish() {
		abstractTransaction.changeState(new Completed(abstractTransaction));
	}
}
