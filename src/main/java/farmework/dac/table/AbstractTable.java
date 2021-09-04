package farmework.dac.table;

import farmework.dac.builder.StatementBuilder;
import farmework.dac.service.JdbcService;
import farmework.dac.sql.SqlGenerator;
import farmework.model.GenSqlParams;
import farmework.model.RowEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public abstract class AbstractTable {

    protected String dbName;

    protected String tableName;

    public AbstractTable(String dbName, String tableName) {
        this.dbName = dbName;
        this.tableName = tableName;
    }

    public int delete(StatementBuilder statementBuilder) {
        Pair<String, Map<String, Object>> sqlParamPair = SqlGenerator.genDeleteSql(new GenSqlParams(this.dbName, this.tableName, statementBuilder.getFields()));
        return new JdbcService().modify(sqlParamPair.getLeft(), sqlParamPair.getRight());
    }

    public List<RowEntity> query(StatementBuilder statementBuilder) {
        Pair<String, Map<String, Object>> sqlParamPair = SqlGenerator.genSelectSql(new GenSqlParams(this.dbName, this.tableName, statementBuilder.getFields()));
        return new JdbcService().query(sqlParamPair.getLeft(), sqlParamPair.getRight());
    }
}
