package cz.codingmonkeys.ibs.domain.transactions;

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
	public ChangeStateResult certify(String response) {
		ChangeStateResult result = super.certify(response);
		if (!result.isSuccess()) {
			return result;
		}
		return finish();
	}

	public String getUser() {
		return user;
	}

	public static TwoPhaseAuthenticationTransaction createTwoPhaseAuthenticationTransactionForUser(String user) {
		return new TwoPhaseAuthenticationTransaction(user);
	}
}
