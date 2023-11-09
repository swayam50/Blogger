package io.wulfcodes.blogger.rest.provider.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jakarta.ws.rs.NameBinding;
import jakarta.ws.rs.ext.Provider;
import io.wulfcodes.blogger.rest.authenticator.Authenticator;
import io.wulfcodes.blogger.rest.authenticator.PassThroughAuthenticator;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

@Retention(RUNTIME)
@Target({TYPE, METHOD})
@NameBinding
@Provider
public @interface Authenticated {

    Class<? extends Authenticator> by() default PassThroughAuthenticator.class;

    Class<? extends Authenticator> or() default PassThroughAuthenticator.class;


}
