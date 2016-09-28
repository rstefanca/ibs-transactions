package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author Richard Stefanca
 */
public class Completed extends TransactionState {
	Completed(TransactionState transactionState) {
		super(transactionState.getTransaction());
	}
}
