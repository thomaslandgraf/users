package com.landgraf.users.infrastructure.exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException  extends AuthenticationException {

    public UnauthorizedException(@Nullable String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnauthorizedException(@Nullable String msg) {
        super(msg);
    }
}
