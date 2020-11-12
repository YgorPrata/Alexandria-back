package com.restapp.resource.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
/*annotation personalizada para assegurar os endpoints com restrição de acesso*/
public @interface Seguro {
	
	UserRoles[] value() default{};
}
