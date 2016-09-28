package cz.codingmonkeys.ibs.dto;

import lombok.Value;

/**
 * @author Richard Stefanca
 */
@Value
public class ConfirmTransactionDto {
	public final long id;
	public final String tokenResponse;


}
