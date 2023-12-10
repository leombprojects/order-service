package org.leombprojects.order.configs.security.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ErrorResponse {
    @JsonProperty("correlation_id")
    private UUID correlationId;
    @JsonProperty("code")
    private String errorCode;
    @JsonProperty("error_description")
    private String errorDescription;

}
