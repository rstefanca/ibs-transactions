package cz.codingmonkeys.ibs.dto;

/**
 * @author rstefanca
 */

public class TransactionDto {

	public final long id;
	public final String type;
	public final String status;

	public TransactionDto(long id, String type, String status) {
		this.id = id;
		this.type = type;
		this.status = status;
	}
}
