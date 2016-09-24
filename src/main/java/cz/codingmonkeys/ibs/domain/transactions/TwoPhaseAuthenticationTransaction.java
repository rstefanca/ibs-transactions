package cz.codingmonkeys.ibs.domain.transactions;

import cz.codingmonkeys.ibs.domain.transactions.states.Initialized;

/**
 * @author Richard Stefanca
 */

public class TwoPhaseAuthenticationTransaction extends AbstractTransaction {

	private final String user;

	private TwoPhaseAuthenticationTransaction(String user) {
		this.user = user;
		this.state = new Initialized(this);
	}

	@Override
	public void certify(String response) {
		super.certify(response);
		finish();
	}

	public String getUser() {
		return user;
	}

	public static TwoPhaseAuthenticationTransaction createTwoPhaseAuthenticationTransactionForUser(String user) {
		return new TwoPhaseAuthenticationTransaction(user);
	}
}
