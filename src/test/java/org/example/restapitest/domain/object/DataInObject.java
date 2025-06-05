package org.example.restapitest.domain.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("cucumber-glue")
public class DataInObject {

    @JsonProperty("year")
    private Integer year = null;
    @JsonProperty("price")
    private Double price = null;
    @JsonProperty("CPU model")
    private String cpuModel;
    @JsonProperty("Hard disk size")
    private String hardDiskSize;
    @JsonProperty("generation")
    private String generation;
    @JsonProperty("color")
    private String color;
    @JsonProperty("capacity")
    private String capacity;

}
