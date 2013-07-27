/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.Annotation;

/**
 *
 * @author igor
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SignedInPlayerNotImportant {
    
}
