package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author Richard Stefanca
 */
public class Certified extends TransactionState {
	Certified(TransactionState transactionState) {
		super(transactionState.getTransaction());
	}

	@Override
	ChangeStateResult finish() {
		return changeState(new Completed(this));
	}
}
