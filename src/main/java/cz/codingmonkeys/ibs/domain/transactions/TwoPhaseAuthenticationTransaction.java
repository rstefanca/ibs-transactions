package cz.codingmonkeys.ibs.domain.transactions;

import lombok.NonNull;

/**
 * @author Richard Stefanca
 */

public class TwoPhaseAuthenticationTransaction extends AbstractTransaction {

	private final String user;

	private TwoPhaseAuthenticationTransaction(@NonNull String user) {
		super();
		this.user = user;
	}

	public static TwoPhaseAuthenticationTransaction createTwoPhaseAuthenticationTransactionForUser(String user) {
		return new TwoPhaseAuthenticationTransaction(user);
	}

	@Override
	public ChangeStateResult certify(String response) {
		ChangeStateResult result = super.certify(response);
		return result.isSuccess() ? finish() : result;
	}

	public String getUser() {
		return user;
	}
}
