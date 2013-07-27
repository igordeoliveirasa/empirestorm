package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.PlayerSkills;

@Component
public class PlayerSkillsRepositoryImpl
    extends Repository<PlayerSkills, Long>
    implements PlayerSkillsRepository {

	PlayerSkillsRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
