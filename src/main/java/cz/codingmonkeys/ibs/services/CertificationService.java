package cz.codingmonkeys.ibs.services;

import com.google.common.eventbus.Subscribe;
import cz.codingmonkeys.ibs.App;
import cz.codingmonkeys.ibs.domain.transactions.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.beans.PropertyChangeEvent;

/**
 * @author Richard Stefanca
 */

@Component
@Log4j
public class CertificationService {

	@Autowired
	private OtpService otpService;


	@PostConstruct
	public void init() {
		log.info("Subscribing...");
		App.eventBus.register(this);
	}

	@Subscribe
	public void onPropertyChange(PropertyChangeEvent propertyChangeEvent) {
		if (propertyChangeEvent.getNewValue() instanceof WaitingForCertification &&
				propertyChangeEvent.getSource() instanceof ChangeLoginSettingsTransaction) {
			ChangeLoginSettingsTransaction source = (ChangeLoginSettingsTransaction) propertyChangeEvent.getSource();
			otpService.sendOtp(source.getDirectChannelUser().getSmsNumber(), source.getSignature().getChallenge());
		}
	}
}
