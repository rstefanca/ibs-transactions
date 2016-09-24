package cz.codingmonkeys.ibs.domain.transactions.states;

import cz.codingmonkeys.ibs.domain.transactions.AbstractTransaction;
import cz.codingmonkeys.ibs.domain.transactions.TransactionState;

/**
 * @author Richard Stefanca
 */
public class WaitingForCertification extends TransactionState  {

	public WaitingForCertification(AbstractTransaction abstractTransaction) {
		super(abstractTransaction);

	}

	@Override
	public void certify(String response) {
		if (abstractTransaction.getSignature().confirmWithResponse(response)) {
			abstractTransaction.changeState(new Certified(abstractTransaction));
		}

	}
}
