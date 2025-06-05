package org.example.restapitest.domain.object;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Scope("cucumber-glue")
public class ObjectItems {

    private List<ObjectItem> objectItemList;
}
