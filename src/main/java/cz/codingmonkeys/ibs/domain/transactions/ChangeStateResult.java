package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author rstefanca
 */
public abstract class ChangeStateResult {

	static ChangeStateResult success() {
		return ChangeStateSuccess.SUCCESS;
	}

	static ChangeStateResult failure(String errorCode) {
		return new ChangeStateFailure(errorCode);
	}

	public abstract String getErrorCode();

	public abstract boolean isSuccess();
}
