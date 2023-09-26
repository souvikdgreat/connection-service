package com.graph.connection.domain.co;

import com.graph.connection.domain.ConnectionStatus;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnectionRespondCO {
    Long userId;
    Long requesterUserId;
    ConnectionStatus status;
}
