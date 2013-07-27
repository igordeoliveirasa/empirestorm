package app.controllers;

import java.util.List;

import app.models.Player;
import app.repositories.PlayerRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class PlayerController {

	private final Result result;
	private final PlayerRepository repository;
	
	private final Validator validator;
	
	public PlayerController(Result result, PlayerRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/players")
	public List<Player> index() {
		return repository.findAll();
	}
	
	@Post("/players")
	public void create(Player player) {
		validator.validate(player);
		validator.onErrorUsePageOf(this).newPlayer();
		repository.create(player);
		result.redirectTo(this).index();
	}
	
	@Get("/players/new")
	public Player newPlayer() {
		return new Player();
	}
	
	@Put("/players")
	public void update(Player player) {
		validator.validate(player);
		validator.onErrorUsePageOf(this).edit(player);
		repository.update(player);
		result.redirectTo(this).index();
	}
	
	@Get("/players/{player.id}/edit")
	public Player edit(Player player) {
		
		return repository.find(player.getId());
	}

	@Get("/players/{player.id}")
	public Player show(Player player) {
		return repository.find(player.getId());
	}

	@Delete("/players/{player.id}")
	public void destroy(Player player) {
		repository.destroy(repository.find(player.getId()));
		result.redirectTo(this).index();  
	}
}