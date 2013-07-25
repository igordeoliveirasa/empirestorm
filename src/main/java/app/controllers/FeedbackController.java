package app.controllers;

import java.util.List;

import app.models.Feedback;
import app.repositories.FeedbackRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class FeedbackController {

	private final Result result;
	private final FeedbackRepository repository;
	
	private final Validator validator;
	
	public FeedbackController(Result result, FeedbackRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/feedbacks")
	public List<Feedback> index() {
		return repository.findAll();
	}
	
	@Post("/feedbacks")
	public void create(Feedback feedback) {
		validator.validate(feedback);
		validator.onErrorUsePageOf(this).newFeedback();
		repository.create(feedback);
		result.redirectTo(this).index();
	}
	
	@Get("/feedbacks/new")
	public Feedback newFeedback() {
		return new Feedback();
	}
	
	@Put("/feedbacks")
	public void update(Feedback feedback) {
		validator.validate(feedback);
		validator.onErrorUsePageOf(this).edit(feedback);
		repository.update(feedback);
		result.redirectTo(this).index();
	}
	
	@Get("/feedbacks/{feedback.id}/edit")
	public Feedback edit(Feedback feedback) {
		
		return repository.find(feedback.getId());
	}

	@Get("/feedbacks/{feedback.id}")
	public Feedback show(Feedback feedback) {
		return repository.find(feedback.getId());
	}

	@Delete("/feedbacks/{feedback.id}")
	public void destroy(Feedback feedback) {
		repository.destroy(repository.find(feedback.getId()));
		result.redirectTo(this).index();  
	}
}