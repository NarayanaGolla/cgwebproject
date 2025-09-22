package com.cog.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final CustomUserDetailsService userDetailsServiceImpl;
  private final JwtService jwtService;

  @Autowired
  public JwtAuthFilter(CustomUserDetailsService userDetailsServiceImpl, JwtService jwtService) {
    this.userDetailsServiceImpl = userDetailsServiceImpl;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;

    // ✅ Skip JWT check for public endpoints
    String path = request.getRequestURI();
    if (path.equals("/index")
        || path.equals("/login")
        || path.equals("/register")
        || path.startsWith("/css/")
        || path.startsWith("/images/")) {
      filterChain.doFilter(request, response);
      return;
    }

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
      username = jwtService.extractUsername(token);
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

      if (jwtService.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // ✅ Tell Spring Security the request is authenticated
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
