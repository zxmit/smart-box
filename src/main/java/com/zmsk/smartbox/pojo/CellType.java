package com.zmsk.smartbox.pojo;

public class CellType {

    private int typeId;
    private String cellTypeName;
    private int xLength;
    private int yLength;
    private int zLength;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getCellTypeName() {
        return cellTypeName;
    }

    public void setCellTypeName(String cellTypeName) {
        this.cellTypeName = cellTypeName;
    }

    public int getxLength() {
        return xLength;
    }

    public void setxLength(int xLength) {
        this.xLength = xLength;
    }

    public int getyLength() {
        return yLength;
    }

    public void setyLength(int yLength) {
        this.yLength = yLength;
    }

    public int getzLength() {
        return zLength;
    }

    public void setzLength(int zLength) {
        this.zLength = zLength;
    }
}
