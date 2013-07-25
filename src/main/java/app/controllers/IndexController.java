package app.controllers;

import java.util.List;

import app.models.Feedback;
import app.repositories.FeedbackRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
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
        private SessionManager sessionManager;
        
	public IndexController(Result result, Validator validator, SessionManager sessionManager) {
            this.result = result;
            this.validator = validator;
            this.sessionManager = sessionManager;
	}
	
        @Post
        @Get
        @Path("/")
        public void login() {            
            result.permanentlyRedirectTo(sessionManager.getFacebook().getOAuthAuthorizationURL("http://www.empirestorm.com/index"));
        }
        
        @Post
        @Get
        @Path("/index")
        public void index(String code) {
            try {
                sessionManager.getFacebook().getOAuthAccessToken(code);
                result.include("signedIn", sessionManager.getFacebook().getMe()!=null);
            } catch (FacebookException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }	
}