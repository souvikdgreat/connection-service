package com.graph.connection.domain.co;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Connection request body")
public class ConnectionRequestCO {
    @Schema(description = "User who is initiating the request", example = "3")
    Long userId;
    @Schema(description = "User to connect to", example = "4")
    Long requestedUserId;
}
