package cz.codingmonkeys.ibs.repositories;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Richard Stefanca
 */
public interface SimpleTransaction {

	long getId();

	@Value("#{target.toString}")
	String getType();

	@Value("#{target.state.toString")
	String getStatus();


}
