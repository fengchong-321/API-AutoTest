package farmework.gen.model;

public class WriteFileContext {
    private String javaFilePath;
    private boolean isCleanFirst;
    private String replaceCode;
    private String originFileCode;

    private WriteFileContext(Builder builder) {
        this.javaFilePath = builder.javaFilePath;
        this.isCleanFirst = builder.isCleanFirst;
        this.replaceCode = builder.replaceCode;
    }

    public void addOriginFileCode(String originFileCode) {
        this.originFileCode = originFileCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String javaFilePath;
        private boolean isCleanFirst;
        private String replaceCode;

        private Builder() {
        }

        public Builder javaFilePath(String javaFilePath) {
            this.javaFilePath = javaFilePath;
            return this;
        }

        public Builder isCleanFirst(boolean isCleanFirst) {
            this.isCleanFirst = isCleanFirst;
            return this;
        }

        public Builder replaceCode(String replaceCode) {
            this.replaceCode = replaceCode;
            return this;
        }

        public WriteFileContext build() {
            return new WriteFileContext(this);
        }
    }

    public String getJavaFilePath() {
        return javaFilePath;
    }

    public boolean isCleanFirst() {
        return isCleanFirst;
    }

    public String getReplaceCode() {
        return replaceCode;
    }

    public String getOriginFileCode() {
        return originFileCode;
    }

    @Override
    public String toString() {
        return "WriteFileContext{" +
                "javaFilePath='" + javaFilePath + '\'' +
                ", isCleanFirst=" + isCleanFirst +
                ", replaceCode='" + replaceCode + '\'' +
                ", originFileCode='" + originFileCode + '\'' +
                '}';
    }
}
