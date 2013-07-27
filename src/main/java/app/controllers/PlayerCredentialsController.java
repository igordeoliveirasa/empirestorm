package app.controllers;

import java.util.List;

import app.models.PlayerCredentials;
import app.repositories.PlayerCredentialsRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class PlayerCredentialsController {

	private final Result result;
	private final PlayerCredentialsRepository repository;
	
	private final Validator validator;
	
	public PlayerCredentialsController(Result result, PlayerCredentialsRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/playerCredentials")
	public List<PlayerCredentials> index() {
		return repository.findAll();
	}
	
	@Post("/playerCredentials")
	public void create(PlayerCredentials playerCredentials) {
		validator.validate(playerCredentials);
		validator.onErrorUsePageOf(this).newPlayerCredentials();
		repository.create(playerCredentials);
		result.redirectTo(this).index();
	}
	
	@Get("/playerCredentials/new")
	public PlayerCredentials newPlayerCredentials() {
		return new PlayerCredentials();
	}
	
	@Put("/playerCredentials")
	public void update(PlayerCredentials playerCredentials) {
		validator.validate(playerCredentials);
		validator.onErrorUsePageOf(this).edit(playerCredentials);
		repository.update(playerCredentials);
		result.redirectTo(this).index();
	}
	
	@Get("/playerCredentials/{playerCredentials.id}/edit")
	public PlayerCredentials edit(PlayerCredentials playerCredentials) {
		
		return repository.find(playerCredentials.getId());
	}

	@Get("/playerCredentials/{playerCredentials.id}")
	public PlayerCredentials show(PlayerCredentials playerCredentials) {
		return repository.find(playerCredentials.getId());
	}

	@Delete("/playerCredentials/{playerCredentials.id}")
	public void destroy(PlayerCredentials playerCredentials) {
		repository.destroy(repository.find(playerCredentials.getId()));
		result.redirectTo(this).index();  
	}
}