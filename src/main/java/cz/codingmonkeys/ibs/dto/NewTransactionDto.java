package cz.codingmonkeys.ibs.dto;

/**
 * @author rstefanca
 */

public class NewTransactionDto {

	public final long id;
	public final String type;
	public final String status;

	public NewTransactionDto(long id, String type, String status) {
		this.id = id;
		this.type = type;
		this.status = status;
	}
}
