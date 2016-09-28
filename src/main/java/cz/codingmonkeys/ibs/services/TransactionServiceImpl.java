package cz.codingmonkeys.ibs.services;

import cz.codingmonkeys.ibs.domain.DirectChannelUser;
import cz.codingmonkeys.ibs.domain.transactions.ChangeLoginSettingsTransaction;
import cz.codingmonkeys.ibs.domain.transactions.Signature;
import cz.codingmonkeys.ibs.dto.NewTransactionDto;
import cz.codingmonkeys.ibs.dto.RequestLoginChangeDto;
import cz.codingmonkeys.ibs.repositories.DirectChannelUserRepository;
import cz.codingmonkeys.ibs.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cz.codingmonkeys.ibs.domain.transactions.ChangeLoginSettingsTransaction.createNewChangeLoginSettingsTransaction;

/**
 * @author rstefanca
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private DirectChannelUserRepository directChannelUserRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	@Transactional
	public NewTransactionDto startLoginChangeTransaction(RequestLoginChangeDto request) {
		DirectChannelUser dcu = directChannelUserRepository.findOne(request.getUserId());
		ChangeLoginSettingsTransaction tx = createNewChangeLoginSettingsTransaction(
				dcu,
				request.getLoginType()
		);

		tx.waitForCertification(new Signature("1111"));

		transactionRepository.save(tx);

		return new NewTransactionDto(tx.getId(), tx.toString(), tx.getState().toString());
	}


}
