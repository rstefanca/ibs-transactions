package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author rstefanca
 */
class ChangeStateFailure extends ChangeStateResult {

	private final String errorCode;

	ChangeStateFailure(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public boolean isSuccess() {
		return false;
	}

	@Override
	public String toString() {
		return "ChangeStateFailure(" +  errorCode + ')';
	}
}
