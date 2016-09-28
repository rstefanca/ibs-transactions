package cz.codingmonkeys.ibs.services;

import cz.codingmonkeys.ibs.dto.ConfirmTransactionDto;
import cz.codingmonkeys.ibs.dto.TransactionDto;
import cz.codingmonkeys.ibs.dto.RequestLoginChangeDto;

import java.util.Collection;
import java.util.List;

/**
 * @author rstefanca
 */
public interface TransactionService {

	TransactionDto startLoginChangeTransaction(RequestLoginChangeDto request);

	TransactionDto confirmTransaction(ConfirmTransactionDto dto);

	Collection<TransactionDto> loadAll();
}
