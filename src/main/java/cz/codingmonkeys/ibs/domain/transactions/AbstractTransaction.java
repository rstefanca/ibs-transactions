package cz.codingmonkeys.ibs.domain.transactions;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * @author Richard Stefanca
 */
public class AbstractTransaction {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private long id;

	protected TransactionState state;
	private Signature signature;

	private List<TransactionState> history = Lists.newArrayList();

	protected AbstractTransaction() {

	}

	public void waitForCertification(Signature signature) {
		state.waitForCertification(signature);
	}


	public void certify(String response) {
		state.certify(response);
	}

	public void changeState(TransactionState newState) {
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

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	public Signature getSignature() {
		return signature;
	}

	public void finish() {
		state.finish();
	}

	public void addListener(PropertyChangeListener propertyChangeListener) {
		pcs.addPropertyChangeListener(propertyChangeListener);
	}

	public List<TransactionState> getHistory() {
		return ImmutableList.copyOf(history);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
