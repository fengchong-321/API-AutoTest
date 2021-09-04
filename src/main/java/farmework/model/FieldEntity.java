package farmework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldEntity {
    private String filedName;
    private Object fieldValue;
}
