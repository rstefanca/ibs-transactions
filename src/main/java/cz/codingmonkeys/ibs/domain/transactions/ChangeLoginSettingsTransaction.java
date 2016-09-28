package cz.codingmonkeys.ibs.domain.transactions;

import cz.codingmonkeys.ibs.domain.DirectChannelUser;
import lombok.NonNull;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Richard Stefanca
 */
public class ChangeLoginSettingsTransaction extends AbstractTransaction {

	private final DirectChannelUser directChannelUser;
	private final String newMfaType;

	private ChangeLoginSettingsTransaction(@NonNull DirectChannelUser directChannelUser, @NonNull String newMfaType) {
		super();
		this.directChannelUser = directChannelUser;
		this.newMfaType = newMfaType;
		addListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				ChangeLoginSettingsTransaction.this.propertyChange(evt);
			}
		});
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
