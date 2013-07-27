package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Player;

@Component
public class PlayerRepositoryImpl
    extends Repository<Player, Long>
    implements PlayerRepository {

	PlayerRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
