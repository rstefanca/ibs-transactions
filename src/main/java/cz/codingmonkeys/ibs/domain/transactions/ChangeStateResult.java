package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author rstefanca
 */
public abstract class ChangeStateResult {

	public abstract String getErrorCode();

	public abstract boolean isSuccess();

	static ChangeStateResult success() {
		return ChangeStateSuccess.SUCCESS;
	}

	static ChangeStateResult failure(String errorCode) {
		return new ChangeStateFailure(errorCode);
	}
}
