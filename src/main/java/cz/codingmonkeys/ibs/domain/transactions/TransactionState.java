package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author Richard Stefanca
 */
public abstract class TransactionState {

	protected AbstractTransaction abstractTransaction;
	private final long timestamp;

	protected TransactionState(AbstractTransaction abstractTransaction) {
		this.abstractTransaction = abstractTransaction;
		this.timestamp = System.currentTimeMillis();
	}

	public void certify(String response) {
		throw new UnsupportedOperationException("Not supported");
	}

	public void waitForCertification(Signature signature) {
		throw new UnsupportedOperationException("Not supported");
	}

	public void finish() {
		throw new UnsupportedOperationException("Not supported");
	}

	public final AbstractTransaction getAbstractTransaction() {
		return abstractTransaction;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
