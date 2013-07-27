package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.PlaceType;

@Component
public class PlaceTypeRepositoryImpl
    extends Repository<PlaceType, Long>
    implements PlaceTypeRepository {

	PlaceTypeRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
