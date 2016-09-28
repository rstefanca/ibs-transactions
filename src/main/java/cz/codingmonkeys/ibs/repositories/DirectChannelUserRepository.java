package cz.codingmonkeys.ibs.repositories;

import cz.codingmonkeys.ibs.domain.DirectChannelUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rstefanca
 */
@Repository
public interface DirectChannelUserRepository extends CrudRepository<DirectChannelUser, Long> {
}
