package com.example.decision_engine.model.request.management;

import com.example.decision_engine.enums.facts.FactDataType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateFactRequest {

    @Size(max = 100, message = "factKey length should be less than 100")
    @NotEmpty(message = "factKey is required")
    private String factKey;

    @Size(max = 100, message = "name length should be less than 100")
    @NotEmpty(message = "name is required")
    private String name;

    @NotNull(message = "type is required")
    private FactDataType type;

}
