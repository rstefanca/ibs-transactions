package cz.codingmonkeys.ibs.domain.transactions;

import cz.codingmonkeys.ibs.domain.DirectChannelUser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Richard Stefanca
 */
public class ChangeLoginSettingsTransaction extends AbstractTransaction implements PropertyChangeListener {

	private final DirectChannelUser directChannelUser;
	private final String newMfaType;

	private ChangeLoginSettingsTransaction(DirectChannelUser directChannelUser, String newMfaType) {
		this.directChannelUser = directChannelUser;
		this.newMfaType = newMfaType;
		this.state = new Initialized(this);
		addListener(this);
	}

	public static ChangeLoginSettingsTransaction createNewChangeLoginSettingsTransaction(DirectChannelUser dcu, String mfaType) {
		return new ChangeLoginSettingsTransaction(dcu, mfaType);
	}

	public DirectChannelUser getDirectChannelUser() {
		return directChannelUser;
	}

	@Override
	public ChangeStateResult certify(String response) {
		ChangeStateResult result = super.certify(response);
		if (!result.isSuccess()) {
			return result;
		}
		return finish();
	}

	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		if (propertyChangeEvent.getNewValue() instanceof Certified) {
			directChannelUser.setMfaType(newMfaType);
		}
	}
}
