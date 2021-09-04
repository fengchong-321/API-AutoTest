package farmework.gen.model;

import com.google.common.collect.Lists;

import java.util.List;

public class GenContext {
    private List<Class<?>> forBeanSetterClasses;
    private String javaFilePath;
    private boolean isCleanFirst;
    private String packageName;
    private String prefixClassName;
    private String suffixClassName;
    private String codeResult;
    private GenType genType;

    private GenContext(Builder builder) {
        this.forBeanSetterClasses = builder.forBeanSetterClasses;
        this.javaFilePath = builder.javaFilePath;
        this.isCleanFirst = builder.isCleanFirst;
        this.packageName = builder.packageName;
        this.prefixClassName = builder.prefixClassName;
        this.suffixClassName = builder.suffixClassName;
        this.genType = builder.genType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void addForBeanSetterClasses(List<Class<?>> forBeanSetterClasses) {
        this.forBeanSetterClasses = forBeanSetterClasses;
    }

    public void addCodeResult(String codeResult) {
        this.codeResult = codeResult;
    }

    public static class Builder {
        private List<Class<?>> forBeanSetterClasses;
        private String javaFilePath;
        private boolean isCleanFirst;
        private String packageName;
        private String prefixClassName;
        private String suffixClassName;
        private GenType genType;

        private Builder() {
            this.forBeanSetterClasses = Lists.newArrayList();
        }

        public Builder genType(GenType genType) {
            this.genType = genType;
            return this;
        }

        public Builder beanClazz(Class<?> beanClazz) {
            this.forBeanSetterClasses.add(beanClazz);
            return this;
        }

        public Builder javaFilePath(String javaFilePath) {
            this.javaFilePath = javaFilePath;
            return this;
        }

        public Builder cleanFirst() {
            isCleanFirst = true;
            return this;
        }

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder prefixClassName(String prefixClassName) {
            this.prefixClassName = prefixClassName;
            return this;
        }

        public Builder suffixClassName(String suffixClassName) {
            this.suffixClassName = suffixClassName;
            return this;
        }

        public GenContext build() {
            return new GenContext(this);
        }
    }

    public List<Class<?>> getForBeanSetterClasses() {
        return forBeanSetterClasses;
    }

    public String getJavaFilePath() {
        return javaFilePath;
    }

    public boolean isCleanFirst() {
        return isCleanFirst;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPrefixClassName() {
        return prefixClassName;
    }

    public String getSuffixClassName() {
        return suffixClassName;
    }

    public String getCodeResult() {
        return codeResult;
    }

    public GenType getGenType() {
        return genType;
    }

    @Override
    public String toString() {
        return "GenContext{" +
                "forBeanSetterClasses=" + forBeanSetterClasses +
                ", javaFilePath='" + javaFilePath + '\'' +
                ", isCleanFirst=" + isCleanFirst +
                ", packageName='" + packageName + '\'' +
                ", prefixClassName='" + prefixClassName + '\'' +
                ", suffixClassName='" + suffixClassName + '\'' +
                ", codeResult='" + codeResult + '\'' +
                ", genType=" + genType +
                '}';
    }
}
