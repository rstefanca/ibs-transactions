package cz.codingmonkeys.ibs;

import cz.codingmonkeys.ibs.domain.DirectChannelUser;
import cz.codingmonkeys.ibs.domain.transactions.*;
import cz.codingmonkeys.ibs.domain.transactions.WaitingForCertification;
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

		ChangeLoginSettingsTransaction tx = createNewChangeLoginSettingsTransaction(dcu, "2FA");
		AbstractTransaction tx2fa = createTwoPhaseAuthenticationTransactionForUser("TBCEI1");
		AbstractTransaction failure = createTwoPhaseAuthenticationTransactionForUser("TBCE1");

		prettyPrintStates(tx, tx2fa, failure);
		printDcu(tx);

		enableSmsNotification(tx);
		enableSmsNotification(tx2fa);

		tx.waitForCertification(new Signature(challenge));
		tx.certify(response);

		tx2fa.waitForCertification(new Signature(challenge));
		tx2fa.certify(response);
		tx2fa.getCreated();

		printHistory(tx);
		printHistory(tx2fa);

		for (TransactionState transactionState : tx2fa.getAllStates()) {
			System.out.println(transactionState);
		}

		System.out.println("FAILURE");

		failure.waitForCertification(new Signature(challenge));
		ChangeStateResult res = failure.certify("xxx");
		System.out.println(res);
		System.out.println(failure.getState());
	}

	private static void printHistory(AbstractTransaction tx) {
		System.out.println("History of " + tx);
		for (TransactionState transactionState : tx.getHistory()) {
			System.out.println(new DateTime(transactionState.getTimestamp()) + " - " + transactionState.toString());
		}
	}

	private static void printDcu(final ChangeLoginSettingsTransaction tx) {
		tx.addListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				System.out.println(tx.getDirectChannelUser());
			}
		});
	}

	private static void prettyPrintStates(AbstractTransaction... transactions) {
		PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				prettyPrint(propertyChangeEvent);
			}
		};

		for (AbstractTransaction tx : transactions) {
			tx.addListener(listener);
		}
	}

	private static void enableSmsNotification(AbstractTransaction... transactions) {
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				if (propertyChangeEvent.getNewValue() instanceof WaitingForCertification) {
					System.out.println("Sending sms with challenge: " + ((WaitingForCertification) propertyChangeEvent.getNewValue()).getTransaction().getSignature().getChallenge());
					System.out.println();
				}
			}
		};

		for (AbstractTransaction tx : transactions) {
			tx.addListener(propertyChangeListener);
		}
	}

	private static void prettyPrint(PropertyChangeEvent propertyChangeEvent) {
		System.out.println(propertyChangeEvent.getSource().toString());
		System.out.println(propertyChangeEvent.getPropertyName() +
				": " +
				propertyChangeEvent.getNewValue() +
				" (previous: " +
				propertyChangeEvent.getOldValue() +
				")\n");
	}
}
