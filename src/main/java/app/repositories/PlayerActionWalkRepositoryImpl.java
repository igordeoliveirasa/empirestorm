package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.PlayerActionWalk;
import app.models.PlayerCredentials;
import java.util.List;
import javax.persistence.Query;

@Component
public class PlayerActionWalkRepositoryImpl
    extends Repository<PlayerActionWalk, Long>
    implements PlayerActionWalkRepository {

	PlayerActionWalkRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

    @Override
    public List<PlayerActionWalk> findAllNotFinalized() {
        Query query = entityManager.createQuery("from " + clazz.getName() + " e where e.finalized = false");
        List<PlayerActionWalk> result = query.getResultList();
        return result;
    }
}
