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
	
	
}