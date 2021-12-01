package com.aeserver.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

  private static final Set<String> SECURED_PATHS = Set.of("/api/aevent", "/api/registration", "/api/user");

  @Value("${jwt.pass-phrase}")
  private String passPhrase;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {

    String servletPath = request.getServletPath();

    if (HttpMethod.OPTIONS.matches(request.getMethod()) || SECURED_PATHS.stream().noneMatch(servletPath::startsWith)) {
      chain.doFilter(request, response);
      return;
    } else {
      try {

        JWToken jwToken = null;

        String encodedToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (encodedToken == null) {
          throw new AuthenticationException("Authentication problem");
        }

        encodedToken = encodedToken.replace("Bearer ", "");

        jwToken = JWToken.decode(encodedToken, this.passPhrase);

        if (jwToken == null) {
          throw new AuthenticationException("Authentication problem");
        }

        request.setAttribute(JWToken.JWT_ATTRIBUTE_NAME, jwToken);
        chain.doFilter(request, response);
        return;

      } catch (AuthenticationException e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication error!");
      }
    }
  }
}
