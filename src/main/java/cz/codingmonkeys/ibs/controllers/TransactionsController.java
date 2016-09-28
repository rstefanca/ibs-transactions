package cz.codingmonkeys.ibs.controllers;

import cz.codingmonkeys.ibs.dto.ConfirmTransactionDto;
import cz.codingmonkeys.ibs.dto.TransactionDto;
import cz.codingmonkeys.ibs.dto.RequestLoginChangeDto;
import cz.codingmonkeys.ibs.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

/**
 * @author rstefanca
 */
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(method = RequestMethod.POST, path = "/changeAuthentication")
	public HttpEntity<TransactionDto> start(@RequestBody RequestLoginChangeDto dto) {
		return ok(transactionService.startLoginChangeTransaction(dto));
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/confirm")
	public HttpEntity<TransactionDto> confirm(@PathVariable(name = "id") long id, @RequestBody ConfirmTransactionDto dto) {
		Assert.isTrue(id == dto.getId());
		return ok(transactionService.confirmTransaction(dto));
	}

	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<Collection<TransactionDto>> list() {
		return ok(transactionService.loadAll());
	}
}
