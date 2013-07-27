/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.interceptors;

import app.controllers.IndexController;
import app.session.SessionManager;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author igor.sa
 */
@Intercepts
@RequestScoped
public class SignedInPlayerVerifier implements Interceptor {
    private HttpServletRequest request;
    private Result result;
    private SessionManager sessionManager;
    
    public SignedInPlayerVerifier(HttpServletRequest request, Result result, SessionManager sessionManager) {
        this.request = request;
        this.result = result;
        this.sessionManager = sessionManager;
    }


    public void intercept(InterceptorStack stack, ResourceMethod method, Object object) throws InterceptionException {
        
        if (!sessionManager.isSignedIn())
        {
            result.redirectTo(IndexController.class).login();
            return;            
        }
        
        stack.next(method, object);
    }

    public boolean accepts(ResourceMethod rm) {
        return !rm.containsAnnotation(SignedInPlayerNotImportant.class);
    }
    
}
