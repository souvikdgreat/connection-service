package com.graph.connection.domain.co;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Follow request body")
public class ConnectionFollowCO {
    @Schema(description = "User who is initiating the request", example = "2")
    Long userId;
    @Schema(description = "User to follow", example = "3")
    Long followingUserId;
}
