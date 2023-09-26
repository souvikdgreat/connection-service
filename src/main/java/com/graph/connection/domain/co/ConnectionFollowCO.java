package com.graph.connection.domain.co;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnectionFollowCO {
    Long userId;
    Long followingUserId;
}
