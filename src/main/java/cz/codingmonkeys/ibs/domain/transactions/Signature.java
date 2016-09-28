package cz.codingmonkeys.ibs.domain.transactions;

import lombok.NonNull;
import lombok.Value;

/**
 * @author Richard Stefanca
 */
@Value
public class Signature {
	@NonNull private final String challenge;

	public boolean confirmWithResponse(String response) {
		return challenge.equals(response);
	}
}
