package com.springstudy.security.jwt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private static final Logger LOG = Logger.getLogger(JwtUtils.class);

    @Value("${dd.app.jwtSecret}")
    private final String jwtSecret = "ddSecretKey";

    @Value("${dd.app.jwtExpirationMs}")
    private final int jwtExpirationMs = 86400000;
}
