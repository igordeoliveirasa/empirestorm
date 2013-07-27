package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.PlayerActionWalk;

@Component
public class PlayerActionWalkRepositoryImpl
    extends Repository<PlayerActionWalk, Long>
    implements PlayerActionWalkRepository {

	PlayerActionWalkRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
