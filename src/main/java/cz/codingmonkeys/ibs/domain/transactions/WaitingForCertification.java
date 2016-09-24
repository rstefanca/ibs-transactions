package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author Richard Stefanca
 */
public class WaitingForCertification extends TransactionState {

	WaitingForCertification(TransactionState transactionState) {
		super(transactionState.getTransaction());
	}

	@Override
	ChangeStateResult certify(String response) {
		ChangeStateResult changeStateResult;
		if (transaction.getSignature().confirmWithResponse(response)) {
			changeStateResult = changeState(new Certified(this));
		} else {
			changeStateResult = failure("Invalid signature");
		}

		return changeStateResult;
	}
}
