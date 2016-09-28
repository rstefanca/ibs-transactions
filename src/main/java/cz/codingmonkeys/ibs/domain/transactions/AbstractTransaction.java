package cz.codingmonkeys.ibs.domain.transactions;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import cz.codingmonkeys.ibs.CurrentTime;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * @author Richard Stefanca
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractTransaction {

	@Transient
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Column
	@Getter private final long created = CurrentTime.currentDateTime();

	@Id
	//@GeneratedValue
	@Getter
	private long id;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "transaction", cascade = CascadeType.ALL)
	@Getter private TransactionState state;

	@Transient
	private final List<TransactionState> history = Lists.newArrayList();

	@Embedded
	@Getter private Signature signature;

	AbstractTransaction() {
		state = new Initialized(this);
	}

	public ChangeStateResult waitForCertification(@NonNull Signature signature) {
		return state.waitForCertification(signature);
	}

	public ChangeStateResult certify(String response) {
		return signature != null ?
				state.certify(response) : ChangeStateResult.failure("Transaction is not supposed to be certified");
	}

	public ChangeStateResult finish() {
		return state.finish();
	}

	void changeState(TransactionState newState) {
		if (!state.equals(newState)) {
			this.pcs.firePropertyChange("state", state, newState);
			addToHistory(state);
			state = newState;
		}
	}

	private void addToHistory(TransactionState state) {
		history.add(state);
	}

	void setSignature(Signature signature) {
		this.signature = signature;
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 */
	public void addListener(PropertyChangeListener propertyChangeListener) {
		pcs.addPropertyChangeListener(propertyChangeListener);
	}

	@Transient
	public List<TransactionState> getHistory() {
		return ImmutableList.copyOf(history);
	}

	@Transient
	public List<TransactionState> getAllStates() {
		List<TransactionState> result = Lists.newArrayListWithCapacity(history.size() + 1);
		result.addAll(history);
		result.add(state);

		return ImmutableList.copyOf(result);
	}

	@Transient
	public boolean isPending() {
		return !(this.getState() instanceof Completed);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
