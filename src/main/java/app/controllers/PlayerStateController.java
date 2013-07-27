package app.controllers;

import java.util.List;

import app.models.PlayerState;
import app.repositories.PlayerStateRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class PlayerStateController {

	private final Result result;
	private final PlayerStateRepository repository;
	
	private final Validator validator;
	
	public PlayerStateController(Result result, PlayerStateRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/playerStates")
	public List<PlayerState> index() {
		return repository.findAll();
	}
	
	@Post("/playerStates")
	public void create(PlayerState playerState) {
		validator.validate(playerState);
		validator.onErrorUsePageOf(this).newPlayerState();
		repository.create(playerState);
		result.redirectTo(this).index();
	}
	
	@Get("/playerStates/new")
	public PlayerState newPlayerState() {
		return new PlayerState();
	}
	
	@Put("/playerStates")
	public void update(PlayerState playerState) {
		validator.validate(playerState);
		validator.onErrorUsePageOf(this).edit(playerState);
		repository.update(playerState);
		result.redirectTo(this).index();
	}
	
	@Get("/playerStates/{playerState.id}/edit")
	public PlayerState edit(PlayerState playerState) {
		
		return repository.find(playerState.getId());
	}

	@Get("/playerStates/{playerState.id}")
	public PlayerState show(PlayerState playerState) {
		return repository.find(playerState.getId());
	}

	@Delete("/playerStates/{playerState.id}")
	public void destroy(PlayerState playerState) {
		repository.destroy(repository.find(playerState.getId()));
		result.redirectTo(this).index();  
	}
}