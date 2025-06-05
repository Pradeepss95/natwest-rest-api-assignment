package org.example.restapitest.domain.object;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Scope("cucumber-glue")
public class ObjectItems {

    private List<ObjectItem> objectItemList;
}
