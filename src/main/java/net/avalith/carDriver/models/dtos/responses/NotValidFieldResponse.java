package net.avalith.carDriver.models.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotValidFieldResponse {

    private String field;

    private String message;

}
