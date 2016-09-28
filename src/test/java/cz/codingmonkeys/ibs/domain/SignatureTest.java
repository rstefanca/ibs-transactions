package cz.codingmonkeys.ibs.domain;

import cz.codingmonkeys.ibs.domain.transactions.Signature;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Richard Stefanca
 */
public class SignatureTest {

	@Test
	public void signatureShouldBeConfirmed() throws Exception {
		String challenge = "1111";
		String response = "1111";

		Signature signature = new Signature(challenge);

		assertTrue(signature.confirmWithResponse(response));

	}
}