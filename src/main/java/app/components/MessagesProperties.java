/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.components;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.I18nMessage;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author igor
 */
@Component
public class MessagesProperties {
    public String getMessage(String category, String key) {
        I18nMessage message = new I18nMessage(category, key);
        message.setBundle(ResourceBundle.getBundle("messages", new Locale("pt", "BR")));
        return message.getMessage();
    }
    
    public String getMessage(String key) {
        return getMessage("", key);
    }
}
