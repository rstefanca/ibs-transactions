package cz.codingmonkeys.ibs.domain.transactions;

import javax.persistence.Entity;

/**
 * @author Richard Stefanca
 */
@Entity
public class Initialized extends TransactionState {

	Initialized(Transaction transaction) {
		super(transaction);
	}

	@Override
	ChangeStateResult waitForCertification(Signature signature) {
		transaction.setSignature(signature);
		return changeState(new WaitingForCertification(this));
	}
}
