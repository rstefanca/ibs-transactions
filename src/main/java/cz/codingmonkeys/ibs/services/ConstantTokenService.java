package cz.codingmonkeys.ibs.services;

import org.springframework.stereotype.Component;

/**
 * @author Richard Stefanca
 */

@Component
public class ConstantTokenService implements TokenService {
	@Override
	public String generateToken() {
		return "1111";
	}
}
