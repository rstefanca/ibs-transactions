package cz.codingmonkeys.ibs;

import cz.codingmonkeys.ibs.domain.DirectChannelUser;
import cz.codingmonkeys.ibs.domain.transactions.*;
import cz.codingmonkeys.ibs.domain.transactions.states.WaitingForCertification;
import org.joda.time.DateTime;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static cz.codingmonkeys.ibs.domain.transactions.ChangeLoginSettingsTransaction.createNewChangeLoginSettingsTransaction;
import static cz.codingmonkeys.ibs.domain.transactions.TwoPhaseAuthenticationTransaction.createTwoPhaseAuthenticationTransactionForUser;

/**
 * @author Richard Stefanca
 */
public class App {

	public static void main(String[] args) {
		final DirectChannelUser dcu = new DirectChannelUser("1FA");
		String response = "1111";
		String challenge = "1111";

		AbstractTransaction tx = createNewChangeLoginSettingsTransaction(dcu, "2FA");
		printDcu(dcu, tx);
		prettyPrintStates(tx);
		enableSmsNotification(tx);

		tx.waitForCertification(new Signature(challenge));
		tx.certify(response);

		AbstractTransaction tx2fa = createTwoPhaseAuthenticationTransactionForUser("TBCEI1");
		prettyPrintStates(tx2fa);
		enableSmsNotification(tx2fa);

		tx2fa.waitForCertification(new Signature("2222"));
		tx2fa.certify("2222");

		printHistory(tx);
		printHistory(tx2fa);
	}

	private static void printHistory(AbstractTransaction tx) {
		System.out.println("History of: " + tx);
		for (TransactionState transactionState : tx.getHistory()) {
			System.out.println(new DateTime(transactionState.getTimestamp()) + " - " + transactionState.toString());
		}
	}

	private static void printDcu(DirectChannelUser dcu, AbstractTransaction tx) {
		tx.addListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				System.out.println(((ChangeLoginSettingsTransaction) propertyChangeEvent.getSource()).getDirectChannelUser());
			}
		});
	}

	private static void prettyPrintStates(AbstractTransaction tx) {
		tx.addListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				prettyPrint(propertyChangeEvent);
			}
		});
	}

	private static void enableSmsNotification(AbstractTransaction tx) {
		tx.addListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				if (propertyChangeEvent.getNewValue() instanceof WaitingForCertification) {
					System.out.println("Sending sms with chalenge: " + ((WaitingForCertification) propertyChangeEvent.getNewValue()).getAbstractTransaction().getSignature().getChallenge());
					System.out.println();
				}
			}
		});
	}

	private static void prettyPrint(PropertyChangeEvent propertyChangeEvent) {
		System.out.println(propertyChangeEvent.getSource().toString());
		System.out.println(propertyChangeEvent.getPropertyName() +
				": " +
				propertyChangeEvent.getOldValue() +
				" -> " +
				propertyChangeEvent.getNewValue() +
				"\n");
	}

	private static void prettyPrint(PropertyChangeEvent propertyChangeEvent, DirectChannelUser dcu) {
		prettyPrint(propertyChangeEvent);
		System.out.println("dcu: " + dcu);
	}
}
