package com.zmsk.smartbox.pojo;

public class CellCount {

    private String typeId;
    private String typeName;
    private String count;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CellCount{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", count=" + count +
                '}';
    }
}
