package cz.codingmonkeys.ibs.services;

import cz.codingmonkeys.ibs.dto.NewTransactionDto;
import cz.codingmonkeys.ibs.dto.RequestLoginChangeDto;

/**
 * @author rstefanca
 */
public interface TransactionService {

	NewTransactionDto startLoginChangeTransaction(RequestLoginChangeDto request);

}
