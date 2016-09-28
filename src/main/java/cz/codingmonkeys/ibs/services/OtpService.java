package cz.codingmonkeys.ibs.services;

/**
 * @author Richard Stefanca
 */
public interface OtpService {

	 void sendOtp(String mobileNumber, String otp);
}
