package app.controllers;

import java.util.List;

import app.models.PlayerSkills;
import app.repositories.PlayerSkillsRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class PlayerSkillsController {

	private final Result result;
	private final PlayerSkillsRepository repository;
	
	private final Validator validator;
	
	public PlayerSkillsController(Result result, PlayerSkillsRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/playerSkills")
	public List<PlayerSkills> index() {
		return repository.findAll();
	}
	
	@Post("/playerSkills")
	public void create(PlayerSkills playerSkills) {
		validator.validate(playerSkills);
		validator.onErrorUsePageOf(this).newPlayerSkills();
		repository.create(playerSkills);
		result.redirectTo(this).index();
	}
	
	@Get("/playerSkills/new")
	public PlayerSkills newPlayerSkills() {
		return new PlayerSkills();
	}
	
	@Put("/playerSkills")
	public void update(PlayerSkills playerSkills) {
		validator.validate(playerSkills);
		validator.onErrorUsePageOf(this).edit(playerSkills);
		repository.update(playerSkills);
		result.redirectTo(this).index();
	}
	
	@Get("/playerSkills/{playerSkills.id}/edit")
	public PlayerSkills edit(PlayerSkills playerSkills) {
		
		return repository.find(playerSkills.getId());
	}

	@Get("/playerSkills/{playerSkills.id}")
	public PlayerSkills show(PlayerSkills playerSkills) {
		return repository.find(playerSkills.getId());
	}

	@Delete("/playerSkills/{playerSkills.id}")
	public void destroy(PlayerSkills playerSkills) {
		repository.destroy(repository.find(playerSkills.getId()));
		result.redirectTo(this).index();  
	}
}