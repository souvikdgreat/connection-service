package com.graph.connection.domain.co;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Cursor request query param")
public class Cursor {
    public static final Cursor DEFAULT = new Cursor();

    @Schema(description = "No of records to skip", example = "0", defaultValue = "0")
    Integer skip = 0;
    @Schema(description = "No of records to fetch", example = "20", defaultValue = "20")
    Integer limit = 20;
}
