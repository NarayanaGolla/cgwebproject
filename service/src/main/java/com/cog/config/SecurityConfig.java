package com.cog.config;

import com.cog.jwt.CustomUserDetailsService;
import com.cog.jwt.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired private JwtAuthFilter jwtAuthFilter;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .cors(cors -> {}) // ✅ Enable CORS// Disable CSRF for testing (optional, enable in prod)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/", "/index", "/register", "/login")
                    .permitAll()
                    .requestMatchers(HttpMethod.OPTIONS, "/**")
                    .permitAll() // ✅ Allow preflight
                    .requestMatchers(
                        "/",
                        "/home",
                        "/index",
                        "/error",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**")
                    .permitAll() // Public endpoint
                    .requestMatchers("/actuator/**") // ✅ Allow actuator endpoints
                    .permitAll()

                    //  Swagger endpoints (important)
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
                    .permitAll()
                    .requestMatchers("/kafka/**")
                    .authenticated()
                    .anyRequest()
                    .authenticated() // All others require authentication
            )
        .formLogin(form -> form.disable()) // disable default form login
        .httpBasic(basic -> basic.disable()); // disable basic auth if not using it

    // Add custom JWT filter before Spring Security's UsernamePasswordAuthenticationFilter
    http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    http.authenticationProvider(authenticationProvider());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }
}
