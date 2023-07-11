package com.NikolaTabas94rn.cinema.configuration;

import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.service.CinemaUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CinemaUserDetailsService userDetailsService;

    private final ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                //users
                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers("/users/{id}")
                .access("@usersGuard.checkUserId(authentication, #id)")
                //movies
                .antMatchers(HttpMethod.GET, "/movies/**").permitAll()
                .antMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
                .antMatchers("/movies/{id}").hasRole("ADMIN")
                //auditoriums
                .antMatchers(HttpMethod.GET, "/auditoriums/**").permitAll()
                .antMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
                .antMatchers("/movies/{id}").hasRole("ADMIN")
                //seats
                .antMatchers(HttpMethod.GET, "/seats/**").permitAll()
                .antMatchers(HttpMethod.POST, "/seats").hasRole("ADMIN")
                .antMatchers("/seats/{id}").hasRole("ADMIN")
                //screenings
                .antMatchers(HttpMethod.GET, "/screenings/**").permitAll()
                .antMatchers(HttpMethod.POST, "/screenings").hasRole("ADMIN")
                .antMatchers("/screenings/{id}").hasRole("ADMIN")
                //seatReserved
                .antMatchers(HttpMethod.GET, "/seats-reserved/screening/{screeningId}").permitAll()
                .antMatchers(HttpMethod.GET, "/seats-reserved/reservation/{reservationId}").hasRole("ADMIN")
                //reservations
                .antMatchers("/screenings/{screeningId}/reservations/{reservationId}")
                .access("@usersGuard.checkUserId(authentication, #id)")
                .antMatchers(HttpMethod.GET, "/users/{userId}/reservations").permitAll()
                .antMatchers(HttpMethod.GET, "/screenings/{screeningId}/reservations").hasRole("ADMIN")
                .and()
                .httpBasic()
                .authenticationEntryPoint(((request, response, authException) -> {
                    ErrorInfo errorInfo = ErrorInfo.builder()
                            .errorType(ErrorInfo.ErrorType.AUTHENTICATION)
                            .resourceType(ErrorInfo.ResourceType.ACCESS)
                            .message("Failed to authenticate user. Bad username and/or password")
                            .build();

                    response.setContentType("application/json;charset=UTF-8");
                    response.setHeader("WWW-Authenticate", "Basic");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
                }))
                .and()
                .exceptionHandling()
                .accessDeniedHandler(((request, response, accessDeniedException) -> {
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    String message = "User " + (auth != null ? auth.getName() : "'unknown'") +
                            " attempted to access the protected URL: " + request.getRequestURI();

                    ErrorInfo errorInfo = ErrorInfo.builder()
                            .errorType(ErrorInfo.ErrorType.UNAUTHORIZED)
                            .resourceType(ErrorInfo.ResourceType.ACCESS)
                            .message(message)
                            .build();

                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
                }));
    }
}
