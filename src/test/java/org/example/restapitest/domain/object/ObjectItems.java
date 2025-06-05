package org.example.restapitest.domain.object;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ObjectItems {

    private List<ObjectItem> objectItemList;
}
