package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author Richard Stefanca
 */
public class Initialized extends TransactionState {

	Initialized(AbstractTransaction abstractTransaction) {
		super(abstractTransaction);
	}

	@Override
	ChangeStateResult waitForCertification(Signature signature) {
		transaction.setSignature(signature);
		return changeState(new WaitingForCertification(this));
	}
}
