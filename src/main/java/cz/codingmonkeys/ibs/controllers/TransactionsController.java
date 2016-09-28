package cz.codingmonkeys.ibs.controllers;

import cz.codingmonkeys.ibs.dto.NewTransactionDto;
import cz.codingmonkeys.ibs.dto.RequestLoginChangeDto;
import cz.codingmonkeys.ibs.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author rstefanca
 */
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(method = RequestMethod.POST, path = "/changeAuthentication")
	public HttpEntity<NewTransactionDto> start(@RequestBody RequestLoginChangeDto dto) {
		return ResponseEntity.ok(transactionService.startLoginChangeTransaction(dto));
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/confirm/{id}")
	public HttpEntity<Void> confirm(@PathVariable(name = "id") long id) {
		return ResponseEntity.noContent().build();
	}
}
