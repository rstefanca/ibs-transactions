package cz.codingmonkeys.ibs.domain.transactions;

/**
 * @author rstefanca
 */
final class ChangeStateSuccess extends ChangeStateResult {

	static final ChangeStateResult SUCCESS = new ChangeStateSuccess();

	public String getErrorCode() {
		throw new IllegalStateException("ChangeStateResult.get() cannot be called on an success value");
	}

	public boolean isSuccess() {
		return true;
	}

	@Override
	public String toString() {
		return "ChangeStateSuccess()";
	}
}
