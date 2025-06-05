package org.example.restapitest.domain.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class ObjectItems {

    private List<ObjectItem> objectItemList;
}
