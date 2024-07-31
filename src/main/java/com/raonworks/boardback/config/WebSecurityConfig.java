package com.raonworks.boardback.config;

import com.raonworks.boardback.exception.ExceptionErrorCode;
import com.raonworks.boardback.filter.JwtAuthenticationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {

    httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(CsrfConfigurer::disable)
            .httpBasic(HttpBasicConfigurer::disable)
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(request -> request
                    .requestMatchers("/", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/", "/api/v1/board/**", "/api/v1/user/**").permitAll()
                    .anyRequest().authenticated())
            .exceptionHandling(exceptionHandle -> exceptionHandle.authenticationEntryPoint(new FailedAuthenticationEntryPoint()))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
  }

  @Bean
  protected CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*")); // 프론트엔드 주소를 명시적으로 지정
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.addExposedHeader("*");
    configuration.addAllowedHeader("*");
    configuration.setAllowCredentials(false);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write("{'code':'" + ExceptionErrorCode.NO_PERMISSION.getCode() + "', 'message':'" + ExceptionErrorCode.NO_PERMISSION.getMessage() + "'}");
  }

}
