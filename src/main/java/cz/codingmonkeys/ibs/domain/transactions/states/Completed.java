package cz.codingmonkeys.ibs.domain.transactions.states;

import cz.codingmonkeys.ibs.domain.transactions.AbstractTransaction;
import cz.codingmonkeys.ibs.domain.transactions.TransactionState;

/**
 * @author Richard Stefanca
 */
public class Completed extends TransactionState {
	protected Completed(AbstractTransaction abstractTransaction) {
		super(abstractTransaction);
	}
}
