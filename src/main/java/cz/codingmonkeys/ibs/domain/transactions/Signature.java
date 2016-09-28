package cz.codingmonkeys.ibs.domain.transactions;

import lombok.NonNull;

import javax.persistence.Embeddable;

/**
 * @author Richard Stefanca
 */

@Embeddable
public class Signature {
	@NonNull
	private String challenge;

	protected Signature() {
	}

	public Signature(@NonNull String challenge) {
		this.challenge = challenge;
	}


	public boolean confirmWithResponse(String response) {
		return challenge.equals(response);
	}
}
