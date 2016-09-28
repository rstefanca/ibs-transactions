package cz.codingmonkeys.ibs.domain.transactions;

import cz.codingmonkeys.ibs.domain.DirectChannelUser;
import lombok.NonNull;

import javax.persistence.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Richard Stefanca
 */
@Entity
public class ChangeLoginSettingsTransaction extends Transaction {

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dcu_id", referencedColumnName = "id", nullable = false)
	private  DirectChannelUser directChannelUser;

	@Column
	private  String newMfaType;

	protected ChangeLoginSettingsTransaction() {
		//for hibernate
		super();
		addListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				ChangeLoginSettingsTransaction.this.propertyChange(evt);
			}
		});
	}

	private ChangeLoginSettingsTransaction(@NonNull DirectChannelUser directChannelUser, @NonNull String newMfaType) {
		this();
		this.directChannelUser = directChannelUser;
		this.newMfaType = newMfaType;
	}

	public static ChangeLoginSettingsTransaction createNewChangeLoginSettingsTransaction(DirectChannelUser dcu, String mfaType) {
		return new ChangeLoginSettingsTransaction(dcu, mfaType);
	}

	public DirectChannelUser getDirectChannelUser() {
		return directChannelUser;
	}

	@Override
	public ChangeStateResult certify(@NonNull String response) {
		ChangeStateResult result = super.certify(response);
		return result.isSuccess() ? finish() : result;
	}

	private void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		if (propertyChangeEvent.getNewValue() instanceof Certified) {
			directChannelUser.setMfaType(newMfaType);
		}
	}
}
