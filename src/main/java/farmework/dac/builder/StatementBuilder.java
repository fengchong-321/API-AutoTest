package farmework.dac.builder;

import com.google.common.collect.Lists;
import farmework.model.StatementEntity;
import farmework.model.StatementOperator;

import java.util.List;

public class StatementBuilder {
    private List<StatementEntity> statements;

    public StatementBuilder() {
        this.statements = Lists.newArrayList();
    }

    public static StatementBuilder builder() {
        return new StatementBuilder();
    }

    public StatementBuilder addStatement(String fieldName, Object fieldValue) {
        this.statements.add(new StatementEntity(fieldName, fieldValue, StatementOperator.EQ));
        return this;
    }

    public StatementBuilder addStatement(String fieldName, Object fieldValue, StatementOperator operator) {
        this.statements.add(new StatementEntity(fieldName, fieldValue, operator));
        return this;
    }

    public List<StatementEntity> getFields() {
        return this.statements;
    }
}
