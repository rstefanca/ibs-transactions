package cz.codingmonkeys.ibs.domain.transactions.states;

import cz.codingmonkeys.ibs.domain.transactions.AbstractTransaction;
import cz.codingmonkeys.ibs.domain.transactions.Signature;
import cz.codingmonkeys.ibs.domain.transactions.TransactionState;

/**
 * @author Richard Stefanca
 */
public class Initialized extends TransactionState {

	public Initialized(AbstractTransaction abstractTransaction) {
		super(abstractTransaction);
	}

	@Override
	public void waitForCertification(Signature signature) {
		abstractTransaction.setSignature(signature);
		abstractTransaction.changeState(new WaitingForCertification(abstractTransaction));
	}
}
