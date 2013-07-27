package app.controllers;

import java.util.List;

import app.models.Feedback;
import app.models.Player;
import app.repositories.FeedbackRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
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
        private final SessionManager sessionManager;
	private final Validator validator;
        private final PlayerRepository playerRepository;
	
	public FeedbackController(Result result, FeedbackRepository repository, 
	Validator validator, SessionManager sessionManager, PlayerRepository playerRepository) {
		this.result = result;
		this.repository = repository;
		this.validator = validator;
                this.sessionManager = sessionManager;
                this.playerRepository = playerRepository;
	}
		
	@Post("/feedbacks")
	public void create(Feedback feedback) {
            validator.validate(feedback);
            validator.onErrorUsePageOf(this).newFeedback();
            feedback.setPlayer(playerRepository.find(sessionManager.getPlayer().getId()));
            repository.create(feedback);
            result.redirectTo(IndexController.class).index();
	}
	
	@Get("/feedbacks/new")
	public Feedback newFeedback() {
            return new Feedback();
	}		
}