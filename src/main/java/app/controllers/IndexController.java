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
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import javax.servlet.http.HttpServletRequest;

@Resource
public class IndexController {

	private final Result result;
	private final Validator validator;
	private HttpServletRequest request;
	public IndexController(Result result,
	Validator validator, HttpServletRequest request) {
		this.result = result;
		this.validator = validator;
                this.request = request;
	}
	
	@Get("/facebookLogin")
	public void facebookLogin() {
            Facebook facebook = new FacebookFactory().getInstance();
            facebook.setOAuthAppId("152531978232583", "c4d682df0073e6d4e6686d15bff3069e");
            //facebook.setOAuthPermissions(commaSeparetedPermissions);
            //facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
            
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/");
            
            result.redirectTo(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
	}
	
}