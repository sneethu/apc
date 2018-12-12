package com.ingg.appaunthentication.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import com.ingg.appaunthentication.model.JwtAuthenticationToken;


public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter{
	

	public JWTAuthenticationFilter() {
		super("/meetings/**");

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rep){

		String header = req.getHeader("Authorisation");
		if (header == null || !header.startsWith("Token")){
			throw new RuntimeException("JWT Token is missing");
		}
		String authenticationToken = header.substring(6);
		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
	
    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }	
}
