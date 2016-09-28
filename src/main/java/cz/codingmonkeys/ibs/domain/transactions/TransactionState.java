package cz.codingmonkeys.ibs.domain.transactions;

import cz.codingmonkeys.ibs.CurrentTime;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @author Richard Stefanca
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "STATE_NAME")
public abstract class TransactionState {

	@Id
	private long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_id", referencedColumnName = "id", nullable = false)
	final AbstractTransaction transaction;

	@Column
	private final long timestamp = CurrentTime.currentDateTime();

	TransactionState(@NonNull AbstractTransaction transaction) {
		this.transaction = transaction;
	}

	ChangeStateResult certify(String response) {
		throw new UnsupportedOperationException("Not supported");
	}

	ChangeStateResult waitForCertification(Signature signature) {
		throw new UnsupportedOperationException("Not supported");
	}

	ChangeStateResult finish() {
		throw new UnsupportedOperationException("Not supported");
	}

	public final AbstractTransaction getTransaction() {
		return transaction;
	}

	public long getTimestamp() {
		return timestamp;
	}

	final ChangeStateResult changeState(TransactionState newState) {
		transaction.changeState(newState);
		return ChangeStateResult.success();
	}

	final ChangeStateResult failure(String errorCode) {
		return ChangeStateResult.failure(errorCode);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
