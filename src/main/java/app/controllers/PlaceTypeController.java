package app.controllers;

import java.util.List;

import app.models.PlaceType;
import app.repositories.PlaceTypeRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class PlaceTypeController {

	private final Result result;
	private final PlaceTypeRepository repository;
	
	private final Validator validator;
	
	public PlaceTypeController(Result result, PlaceTypeRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/placeTypes")
	public List<PlaceType> index() {
		return repository.findAll();
	}
	
	@Post("/placeTypes")
	public void create(PlaceType placeType) {
		validator.validate(placeType);
		validator.onErrorUsePageOf(this).newPlaceType();
		repository.create(placeType);
		result.redirectTo(this).index();
	}
	
	@Get("/placeTypes/new")
	public PlaceType newPlaceType() {
		return new PlaceType();
	}
	
	@Put("/placeTypes")
	public void update(PlaceType placeType) {
		validator.validate(placeType);
		validator.onErrorUsePageOf(this).edit(placeType);
		repository.update(placeType);
		result.redirectTo(this).index();
	}
	
	@Get("/placeTypes/{placeType.id}/edit")
	public PlaceType edit(PlaceType placeType) {
		
		return repository.find(placeType.getId());
	}

	@Get("/placeTypes/{placeType.id}")
	public PlaceType show(PlaceType placeType) {
		return repository.find(placeType.getId());
	}

	@Delete("/placeTypes/{placeType.id}")
	public void destroy(PlaceType placeType) {
		repository.destroy(repository.find(placeType.getId()));
		result.redirectTo(this).index();  
	}
}