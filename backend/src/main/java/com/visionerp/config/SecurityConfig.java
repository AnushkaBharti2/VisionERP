package com.visionerp.config;

import com.visionerp.security.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;
import java.util.*;

@Configuration @EnableMethodSecurity
public class SecurityConfig {
 @Value("${VISIONERP_ALLOWED_ORIGINS:http://localhost:5173,http://127.0.0.1:5173}") private String allowedOrigins;
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain filterChain(HttpSecurity http,JwtFilter jwt)throws Exception{return http
  .csrf(c->c.disable()).cors(c->c.configurationSource(cors())).headers(h->h
    .contentSecurityPolicy(p->p.policyDirectives("default-src 'self'; frame-ancestors 'none'; object-src 'none'; base-uri 'self'"))
    .frameOptions(f->f.deny()).referrerPolicy(r->r.policy(org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)))
  .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  .authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/actuator/health","/ws/**","/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**").permitAll().anyRequest().authenticated())
  .addFilterBefore(jwt,UsernamePasswordAuthenticationFilter.class).build();}
 @Bean CorsConfigurationSource cors(){CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(Arrays.stream(allowedOrigins.split(",")).map(String::trim).filter(x->!x.isBlank()).toList());c.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));c.setAllowedHeaders(List.of("Authorization","Content-Type","Accept","Origin"));c.setAllowCredentials(true);UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;}
}
