package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author Richard Stefanca
 */

public class TwoPhaseAuthenticationTransaction extends AbstractTransaction {

	private final String user;

	private TwoPhaseAuthenticationTransaction(String user) {
		super();
		this.user = user;
	}

	public static TwoPhaseAuthenticationTransaction createTwoPhaseAuthenticationTransactionForUser(String user) {
		return new TwoPhaseAuthenticationTransaction(user);
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
}
