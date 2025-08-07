package com.ctream.scholar.app.config.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_MAX_AGE;
import static org.springframework.web.cors.CorsUtils.isPreFlightRequest;

@Component
@Order( Ordered.HIGHEST_PRECEDENCE )
public class CustomerServiceCorsConfig implements Filter {

    // TODO: refactor following with more up to date approach
    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
        throws ServletException, IOException {

        var response = ( HttpServletResponse ) servletResponse;
        var request = ( HttpServletRequest ) servletRequest;

        response.setHeader( ACCESS_CONTROL_ALLOW_ORIGIN, "*" );
        response.setHeader( ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, GET, OPTIONS, DELETE, PATCH" );
        response.setHeader( ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Content-Type, Location" );
        response.setHeader( ACCESS_CONTROL_EXPOSE_HEADERS, "Location" );
        response.setHeader( ACCESS_CONTROL_MAX_AGE, "3600" );

        if ( isPreFlightRequest( request ) ) {
            response.setStatus( HttpServletResponse.SC_OK );
        } else {
            filterChain.doFilter( request, response );
        }
    }
}
