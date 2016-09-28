package cz.codingmonkeys.ibs.domain.transactions;

import lombok.NonNull;

import javax.persistence.Entity;

/**
 * @author Richard Stefanca
 */
@Entity
public class WaitingForCertification extends TransactionState {

	WaitingForCertification(TransactionState transactionState) {
		super(transactionState.getTransaction());
	}

	@Override
	ChangeStateResult certify(@NonNull String response) {
		return transaction.getSignature().confirmWithResponse(response) ?
				changeState(new Certified(this)) : failure("Invalid signature");
	}
}
