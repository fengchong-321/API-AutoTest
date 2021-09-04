package farmework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatementEntity {
    private String fieldName;
    private Object fieldValue;
    private StatementOperator operator;
}
