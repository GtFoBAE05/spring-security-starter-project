package org.gtfo.learn_spring_security.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gtfo.learn_spring_security.entity.UserAccount;
import org.gtfo.learn_spring_security.service.JwtService;
import org.gtfo.learn_spring_security.service.UserAccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserAccountService userAccountService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtService.parseToken(bearerToken);

        try {
            if (token != null) {
                String id = jwtService.extractSubject(token);
                UserAccount userAccount = userAccountService.getById(id);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAccount, null, userAccount.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Cannot set user authentication {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
