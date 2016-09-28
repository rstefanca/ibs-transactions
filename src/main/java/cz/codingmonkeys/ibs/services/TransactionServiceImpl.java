package cz.codingmonkeys.ibs.services;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.math.BigIntegerMath;
import cz.codingmonkeys.ibs.App;
import cz.codingmonkeys.ibs.domain.DirectChannelUser;
import cz.codingmonkeys.ibs.domain.transactions.Transaction;
import cz.codingmonkeys.ibs.domain.transactions.ChangeLoginSettingsTransaction;
import cz.codingmonkeys.ibs.domain.transactions.Signature;
import cz.codingmonkeys.ibs.dto.ConfirmTransactionDto;
import cz.codingmonkeys.ibs.dto.TransactionDto;
import cz.codingmonkeys.ibs.dto.RequestLoginChangeDto;
import cz.codingmonkeys.ibs.repositories.DirectChannelUserRepository;
import cz.codingmonkeys.ibs.repositories.SimpleTransaction;
import cz.codingmonkeys.ibs.repositories.TransactionRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cz.codingmonkeys.ibs.domain.transactions.ChangeLoginSettingsTransaction.createNewChangeLoginSettingsTransaction;

/**
 * @author rstefanca
 */
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	private static final PropertyChangeListener PROPERTY_CHANGE_LISTENER = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			App.eventBus.post(evt);
		}
	};

	private static final Function<Object[], TransactionDto> TRANSACTION_DTO_FUNCTION = new Function<Object[], TransactionDto>() {
		@Override
		public TransactionDto apply(Object[] input) {
			return new TransactionDto(((BigInteger) input[0]).longValue(), (String) input[1], (String) input[2]);
		}
	};

	@Autowired
	private DirectChannelUserRepository directChannelUserRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TokenService tokenService;

	@Override
	@Transactional
	public TransactionDto startLoginChangeTransaction(@NonNull RequestLoginChangeDto request) {
		DirectChannelUser dcu = directChannelUserRepository.findOne(request.getUserId());
		ChangeLoginSettingsTransaction tx = createNewChangeLoginSettingsTransaction(dcu, request.getLoginType());
		registerStateChangeListener(tx);
		String token = tokenService.generateToken();
		tx.waitForCertification(new Signature(token));
		return toDto(transactionRepository.save(tx));
	}

	@Override
	public TransactionDto confirmTransaction(@NonNull ConfirmTransactionDto dto) {
		Transaction tx = transactionRepository.findOne(dto.getId());
		tx.certify(dto.getTokenResponse());
		return toDto(transactionRepository.save(tx));
	}

	@Override
	public Collection<TransactionDto> loadAll() {
		Iterable<TransactionDto> all = Iterables.transform(transactionRepository.findAllSimple(), TRANSACTION_DTO_FUNCTION);

		return ImmutableList.copyOf(all);
	}

	private static TransactionDto toDto(Transaction tx) {
		return new TransactionDto(tx.getId(), tx.toString(), tx.getState().toString());
	}

	private static void registerStateChangeListener(ChangeLoginSettingsTransaction tx) {
		tx.addListener("state", PROPERTY_CHANGE_LISTENER);
	}
}
