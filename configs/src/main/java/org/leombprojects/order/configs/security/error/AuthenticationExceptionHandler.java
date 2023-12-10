package org.leombprojects.order.configs.security.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(authException.getMessage().contains("expired") ?
                mapper.writeValueAsString(ErrorResponse
                        .builder()
                        .correlationId(UUID.randomUUID())
                        .errorCode("UNAUTHORIZED")
                        .errorDescription(authException.getMessage())
                        .build())
                :
                mapper.writeValueAsString(ErrorResponse
                        .builder()
                        .correlationId(UUID.randomUUID())
                        .errorCode("UNAUTHORIZED")
                        .errorDescription("The token is not valid. Please ensure that you have a valid token.")
                        .build()));
    }
}
