package com.zmsk.smartbox.pojo;

public class Cell {

    private String cellId;
    private String boxId;
    private String cellType;
    private int cellNum;
    private boolean onUse;
    private String typeName;

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public boolean isOnUse() {
        return onUse;
    }

    public void setOnUse(boolean onUse) {
        this.onUse = onUse;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
