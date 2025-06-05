package org.example.restapitest.domain.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Data
@Component
@Scope("cucumber-glue")
public class ObjectItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("data")
    private DataInObject dataInObject;
}
