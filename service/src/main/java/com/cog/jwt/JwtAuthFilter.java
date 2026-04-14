package com.cog.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
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

    // 1️⃣ Read token from session
    String sessionToken = (String) request.getSession().getAttribute("authToken");

    // 2️⃣ Read token from request header
    String authHeader = request.getHeader("Authorization");

    String finalToken = null;

    // Prefer header token if present
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      finalToken = authHeader.substring(7);
    }
    // Otherwise fallback to session token
    else if (sessionToken != null && !sessionToken.isEmpty()) {
      finalToken = sessionToken;
      request = wrapRequestWithToken(request, sessionToken); // Attach token to header
    }

    // 3️⃣ Skip public endpoints
    String path = request.getRequestURI();
    if (path.equals("/index")
        || path.equals("/login")
        || path.equals("/register")
        || path.startsWith("/css/")
        || path.startsWith("/images/")) {
      filterChain.doFilter(request, response);
      return;
    }

    // 4️⃣ Extract username
    String username = null;
    if (finalToken != null) {
      username = jwtService.extractUsername(finalToken);
    }

    // 5️⃣ Validate & authenticate
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

      if (jwtService.validateToken(finalToken, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }

  private HttpServletRequest wrapRequestWithToken(HttpServletRequest request, String token) {
    return new HttpServletRequestWrapper(request) {
      @Override
      public String getHeader(String name) {
        if ("Authorization".equalsIgnoreCase(name)) {
          return "Bearer " + token;
        }
        return super.getHeader(name);
      }
    };
  }
}
