package app.controllers;


import app.models.Feedback;
import app.repositories.FeedbackRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

@Resource
public class FeedbackController {

	private final Result result;
	private final FeedbackRepository repository;
        private final SessionManager sessionManager;
	private final Validator validator;
        private final PlayerRepository playerRepository;
	private List<String> infos;
                
	public FeedbackController(Result result, FeedbackRepository repository, 
	Validator validator, SessionManager sessionManager, PlayerRepository playerRepository) {
            this.result = result;
            this.repository = repository;
            this.validator = validator;
            this.sessionManager = sessionManager;
            this.playerRepository = playerRepository;
            this.infos = new ArrayList<String>();
            result.include("infos", infos);
	}
		
	@Post("/feedbacks")
	public void create(final Feedback feedback) {
            
            validator.checking(new Validations() { {
                that(!Strings.isNullOrEmpty(feedback.getMessage()), "feedback.message", "feedback.message.empty");
            } });            
            validator.onErrorForwardTo(IndexController.class).index();
            
            
            validator.onErrorUsePageOf(this).newFeedback();
            feedback.setPlayer(playerRepository.find(sessionManager.getPlayer().getId()));
            repository.create(feedback);
            result.redirectTo(IndexController.class).index();
            infos.add("O seu feedback foi enviado com sucesso! Agradecemos o apoio!");
	}
	
	@Get("/feedbacks/new")
	public Feedback newFeedback() {
            return new Feedback();
	}		
}