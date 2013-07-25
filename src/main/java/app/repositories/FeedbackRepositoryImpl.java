package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Feedback;

@Component
public class FeedbackRepositoryImpl
    extends Repository<Feedback, Long>
    implements FeedbackRepository {

	FeedbackRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
