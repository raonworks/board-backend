package com.raonworks.boardback.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors(cors -> cors.configure(http))
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
//                    .requestMatchers(HttpMethod.POST, "/", "/public/**", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()  // 여기에 공개 엔드포인트를 지정하세요
//                    .requestMatchers(HttpMethod.GET, "/", "/api/v1/board/**", "/api/v1/user/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/**").permitAll()  // 여기에 공개 엔드포인트를 지정하세요
                            .requestMatchers(HttpMethod.GET, "/**").permitAll()
                            .anyRequest().authenticated()
            )
            .exceptionHandling(handling -> handling.authenticationEntryPoint(new FailedAuthenticationEntryPoint()))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
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
