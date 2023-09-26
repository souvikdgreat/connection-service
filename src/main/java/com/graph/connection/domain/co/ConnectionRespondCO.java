package com.graph.connection.domain.co;

import com.graph.connection.domain.ConnectionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Connection approval request body")
public class ConnectionRespondCO {
    @Schema(description = "User who is initiating the request to approve", example = "5")
    Long userId;
    @Schema(description = "User whose request to approve", example = "6")
    Long requesterUserId;
    @Schema(description = "Status of the connection to make. Approve / Reject", example = "CONNECTED")
    ConnectionStatus status;
}
