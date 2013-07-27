package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.PlayerState;

@Component
public class PlayerStateRepositoryImpl
    extends Repository<PlayerState, Long>
    implements PlayerStateRepository {

	PlayerStateRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
