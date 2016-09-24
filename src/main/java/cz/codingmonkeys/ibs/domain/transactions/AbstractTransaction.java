package cz.codingmonkeys.ibs.domain.transactions;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * @author Richard Stefanca
 */
public abstract class AbstractTransaction {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private Signature signature;

	private TransactionState state;

	private List<TransactionState> history = Lists.newArrayList();

	AbstractTransaction() {
		state = new Initialized(this);
	}

	public ChangeStateResult waitForCertification(Signature signature) {
		return state.waitForCertification(signature);
	}

	public ChangeStateResult certify(String response) {
		return state.certify(response);
	}

	public ChangeStateResult finish() {
		return state.finish();
	}

	void changeState(TransactionState newState) {
		if (!newState.equals(state)) {
			this.pcs.firePropertyChange("state", state, newState);
			addToHistory(state);
			state = newState;
		}
	}

	private void addToHistory(TransactionState state) {
		history.add(state);
	}

	public TransactionState getState() {
		return state;
	}

	public Signature getSignature() {
		return signature;
	}

	void setSignature(Signature signature) {
		this.signature = signature;
	}

	public void addListener(PropertyChangeListener propertyChangeListener) {
		pcs.addPropertyChangeListener(propertyChangeListener);
	}

	public List<TransactionState> getHistory() {
		return ImmutableList.copyOf(history);
	}

	public boolean isPending() {
		return !(this.getState() instanceof Completed);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
