package com.raonworks.boardback.filter;

import com.raonworks.boardback.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    try {
      String token = parseBearerToken(request);
      if (token == null) {
        //pass to next filter.
        filterChain.doFilter(request, response);
        return;
      }

      String email = jwtProvider.validate(token);
      if (email == null) {
        //pass to next filter.(include expired date)
        filterChain.doFilter(request, response);
        return;
      }

      AbstractAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);

      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(authenticationToken);
      SecurityContextHolder.setContext(securityContext);
    } catch(Exception e) {
      e.printStackTrace();
    }

    filterChain.doFilter(request, response);
  }

  private String parseBearerToken(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    boolean hasAuthorization = authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    if(!hasAuthorization) return null;

    return authorizationHeader.substring(7);
  }
}
