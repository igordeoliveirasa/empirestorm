package app.controllers;

import java.util.List;

import app.models.Place;
import app.repositories.PlaceRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class PlaceController {

	private final Result result;
	private final PlaceRepository repository;
	
	private final Validator validator;
	
	public PlaceController(Result result, PlaceRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/places")
	public List<Place> index() {
		return repository.findAll();
	}
		
	@Post("/places")
	public void create(Place place) {
		validator.validate(place);
		validator.onErrorUsePageOf(this).newPlace();
		repository.create(place);
		result.redirectTo(this).index();
	}
	
	@Get("/places/new")
	public Place newPlace() {
		return new Place();
	}
	
	@Put("/places")
	public void update(Place place) {
		validator.validate(place);
		validator.onErrorUsePageOf(this).edit(place);
		repository.update(place);
		result.redirectTo(this).index();
	}
	
	@Get("/places/{place.id}/edit")
	public Place edit(Place place) {
		
		return repository.find(place.getId());
	}

	@Get("/places/{place.id}")
	public Place show(Place place) {
		return repository.find(place.getId());
	}

	@Delete("/places/{place.id}")
	public void destroy(Place place) {
		repository.destroy(repository.find(place.getId()));
		result.redirectTo(this).index();  
	}
}