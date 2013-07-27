package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.PlayerCredentials;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

@Component
public class PlayerCredentialsRepositoryImpl
    extends Repository<PlayerCredentials, Long>
    implements PlayerCredentialsRepository {

	PlayerCredentialsRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

    @Override
    public PlayerCredentials findByUsername(String username) {
        Query query = entityManager.createQuery("from " + clazz.getName() + " e where upper(e.username) = upper(:username)");
        query.setParameter("username", username);
        List<PlayerCredentials> result = query.getResultList();
        if (result.size()>0) {
            return result.get(0);
        }
        return null;
    }
}
