package cz.codingmonkeys.ibs.dto;

import lombok.Value;

/**
 * @author rstefanca
 */

@Value
public class RequestLoginChangeDto {
	private final long userId;
	private final String loginType;


}
