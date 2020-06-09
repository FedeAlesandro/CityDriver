package net.avalith.carDriver.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDtoRequest {

    private String name;
    private Boolean isActive;

    public BrandDtoRequest(String name) {
        this.name = name;
        this.isActive = Boolean.TRUE;
    }
}
