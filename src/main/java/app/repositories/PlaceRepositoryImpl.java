package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Place;
import app.models.PlayerCredentials;
import java.util.List;
import javax.persistence.Query;

@Component
public class PlaceRepositoryImpl
    extends Repository<Place, Long>
    implements PlaceRepository {

	PlaceRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

    @Override
    public Place findByName(String name) {
        Query query = entityManager.createQuery("from " + clazz.getName() + " e where upper(e.name) = upper(:name)");
        query.setParameter("name", name);
        List<Place> result = query.getResultList();
        if (result.size()>0) {
            return result.get(0);
        }
        return null;
    }
}
