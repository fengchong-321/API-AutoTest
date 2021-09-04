package farmework.dac.sql;

import com.google.common.base.Strings;
import farmework.model.GenSqlParams;
import farmework.model.StatementEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;
import java.util.Map;
import java.util.stream.Collectors;

public final class SqlGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlGenerator.class);

    private static final String DELETE_SQL_TEMP = "delete from %s.%s where %s";
    private static final String SELECT_SQL_TEMP = "select * from %s.%s where %s";

    private SqlGenerator() {

    }

    public static Pair<String, Map<String, Object>> genDeleteSql(GenSqlParams params) {
        String statements = params.getStatements().stream()
                .map(statementEntity -> statementEntity.getFieldName() + statementEntity.getOperator().getOperator() + ":" + statementEntity.getFieldName())
                .collect(Collectors.joining(" AND "));

        String sql = new Formatter().format(DELETE_SQL_TEMP, params.getDbName(), params.getTableName(), getActiveStatements(statements)).toString();
        Map<String, Object> paramsMap = params.getStatements().stream()
                .collect(Collectors.toMap(StatementEntity::getFieldName, StatementEntity::getFieldValue));
        LOGGER.info("gen delete sql={}, params = {}", sql, paramsMap);
        return Pair.of(sql, paramsMap);
    }

    public static Pair<String, Map<String, Object>> genSelectSql(GenSqlParams params) {
        String statements = params.getStatements().stream()
                .map(statementEntity -> statementEntity.getFieldName() + statementEntity.getOperator().getOperator() + ":" + statementEntity.getFieldName())
                .collect(Collectors.joining(" AND "));
        String sql = new Formatter().format(SELECT_SQL_TEMP, params.getDbName(), params.getTableName(), getActiveStatements(statements)).toString();
        Map<String, Object> paramsMap = params.getStatements().stream()
                .collect(Collectors.toMap(StatementEntity::getFieldName, StatementEntity::getFieldValue));
        LOGGER.info("gen select sql={}, params = {}", sql, paramsMap);
        return Pair.of(sql, paramsMap);
    }

    private static String getActiveStatements(String statements) {
        return Strings.isNullOrEmpty(statements) ? "" :
                "AND " + statements;
    }
}
