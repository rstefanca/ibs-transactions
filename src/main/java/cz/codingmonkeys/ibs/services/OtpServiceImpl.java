package cz.codingmonkeys.ibs.services;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

/**
 * @author Richard Stefanca
 */

@Component
@Log4j
public class OtpServiceImpl implements OtpService {
	@Override
	public void sendOtp(String mobileNumber, String otp) {
		log.info("Sending sms");
	}
}
