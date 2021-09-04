package farmework.model;

public enum StatementOperator {
    MORE_THAN(">"),
    LESS_THAN("<"),
    EQ("=");

    private String operator;

    StatementOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
