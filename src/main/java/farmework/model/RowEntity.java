package farmework.model;

import java.util.List;

public class RowEntity {
    private List<FieldEntity> fields;

    public RowEntity() {
    }

    public RowEntity(List<FieldEntity> fields) {
        this.fields = fields;
    }

    public List<FieldEntity> getFields() {
        return fields;
    }

    public void setFields(List<FieldEntity> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "RowEntity{" +
                "fields=" + fields +
                '}';
    }
}
