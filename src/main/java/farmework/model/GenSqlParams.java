package farmework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GenSqlParams {
    private String dbName;
    private String tableName;
    private List<StatementEntity> statements;
}
