package org.example.restapitest.domain.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class DataInObject {

    @JsonProperty("year")
    private Integer year = null;
    @JsonProperty("price")
    private Double price = null;
    @JsonProperty("CPU model")
    private String cpuModel;
    @JsonProperty("Hard disk size")
    private String hardDiskSize;

}
