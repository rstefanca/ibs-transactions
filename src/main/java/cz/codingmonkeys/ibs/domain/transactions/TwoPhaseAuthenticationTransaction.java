package cz.codingmonkeys.ibs.domain.transactions;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Richard Stefanca
 */

@Entity
public class TwoPhaseAuthenticationTransaction extends Transaction {

	@Column
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
