package app.controllers;

import java.util.List;

import app.models.Feedback;
import app.repositories.FeedbackRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

@Resource
public class IndexController {

	private final Result result;
	private final Validator validator;
	private HttpServletRequest request;
        private SessionManager sessionManager;
        
	public IndexController(Result result,
	Validator validator, HttpServletRequest request, SessionManager sessionManager) {
		this.result = result;
		this.validator = validator;
                this.request = request;
                this.sessionManager = sessionManager;
	}
	
        @Get("/")
        public void index() {

            try {
                result.include("signedIn", sessionManager.getFacebook().getMe()!=null);
            } catch (Exception ex) {
                result.forwardTo(this).facebookLogin();
            }
        }
        
	@Get("/facebookLogin")
	public void facebookLogin() {
            
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/");
            System.out.println("LOG: " + callbackURL.toString());
            result.redirectTo(sessionManager.getFacebook().getOAuthAuthorizationURL(callbackURL.toString()));
	}
	
}