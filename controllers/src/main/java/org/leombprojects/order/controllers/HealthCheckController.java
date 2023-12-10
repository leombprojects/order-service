package org.leombprojects.order.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leombprojects.models.swagger.api.SuccessHealthCheckDto;
import org.leombprojects.models.swagger.model.HealthApiDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HealthCheckController implements HealthApiDelegate{
    @Override
    public ResponseEntity<SuccessHealthCheckDto> getHealthCheck() {
        return ResponseEntity.ok(SuccessHealthCheckDto.builder().success(true).build());
    }
}
