package com.springBootJWT.SpringJwt_Project.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE}) // @Target tag is used to specify at which type, the annotation is used.
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface  CurrentUser {
}
