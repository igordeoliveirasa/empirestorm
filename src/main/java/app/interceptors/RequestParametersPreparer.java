/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.interceptors;

import app.session.SessionManager;
import app.utils.Constants;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ParametersInstantiatorInterceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

@Intercepts(before=ParametersInstantiatorInterceptor.class)
public class RequestParametersPreparer implements Interceptor {
    
    private MutableRequest mutableRequest;
    
    private static Policy policy = null;
    private static AntiSamy antiSamy = null;
    private HttpServletRequest request;
    private SessionManager sessionManager;
    
    public RequestParametersPreparer(MutableRequest mutableRequest, SessionManager sessionManager, HttpServletRequest request) {
        this.sessionManager = sessionManager;
        this.request = request;
        
        try {
            this.mutableRequest = mutableRequest;
            if (policy==null) {
                policy = Policy.getInstance(Constants.ANTISAMY_CONFIG_FILE_PATH);
                antiSamy = new AntiSamy();
            }
        } catch (PolicyException ex) {
            Logger.getLogger(RequestParametersPreparer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void intercept(InterceptorStack is, ResourceMethod rm, Object o) throws InterceptionException {
        Iterator it = mutableRequest.getParameterMap().entrySet().iterator();

        while (it.hasNext()) {
            try {
                Map.Entry pairs = (Map.Entry)it.next();
                
                String parameterName = (String)pairs.getKey();
                
                // parametros que nao deverao ser tratados
                /*
                if (parameterName.equals("account.css")) {
                    continue;
                }*/
                
                String values[] = (String[]) pairs.getValue();
                
                CleanResults cr = antiSamy.scan(values[0].trim(), policy);
                if (!cr.getErrorMessages().isEmpty()) {
                    
                    String cleanParameterValue = cr.getCleanHTML();

                    mutableRequest.setParameter(parameterName, cleanParameterValue);
                    it.remove();
                }
            } catch (ScanException ex) {
                Logger.getLogger(RequestParametersPreparer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PolicyException ex) {
                Logger.getLogger(RequestParametersPreparer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        is.next(rm, o);
    }

    @Override
    public boolean accepts(ResourceMethod rm) {
        return true;
    }
    
}
/**/