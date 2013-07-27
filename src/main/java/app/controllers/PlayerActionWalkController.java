package app.controllers;

import java.util.List;

import app.models.PlayerActionWalk;
import app.repositories.PlayerActionWalkRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class PlayerActionWalkController {

	private final Result result;
	private final PlayerActionWalkRepository repository;
	
	private final Validator validator;
	
	public PlayerActionWalkController(Result result, PlayerActionWalkRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/playerActionWalks")
	public List<PlayerActionWalk> index() {
		return repository.findAll();
	}
	
	@Post("/playerActionWalks")
	public void create(PlayerActionWalk playerActionWalk) {
		validator.validate(playerActionWalk);
		validator.onErrorUsePageOf(this).newPlayerActionWalk();
		repository.create(playerActionWalk);
		result.redirectTo(this).index();
	}
	
	@Get("/playerActionWalks/new")
	public PlayerActionWalk newPlayerActionWalk() {
		return new PlayerActionWalk();
	}
	
	@Put("/playerActionWalks")
	public void update(PlayerActionWalk playerActionWalk) {
		validator.validate(playerActionWalk);
		validator.onErrorUsePageOf(this).edit(playerActionWalk);
		repository.update(playerActionWalk);
		result.redirectTo(this).index();
	}
	
	@Get("/playerActionWalks/{playerActionWalk.id}/edit")
	public PlayerActionWalk edit(PlayerActionWalk playerActionWalk) {
		
		return repository.find(playerActionWalk.getId());
	}

	@Get("/playerActionWalks/{playerActionWalk.id}")
	public PlayerActionWalk show(PlayerActionWalk playerActionWalk) {
		return repository.find(playerActionWalk.getId());
	}

	@Delete("/playerActionWalks/{playerActionWalk.id}")
	public void destroy(PlayerActionWalk playerActionWalk) {
		repository.destroy(repository.find(playerActionWalk.getId()));
		result.redirectTo(this).index();  
	}
}