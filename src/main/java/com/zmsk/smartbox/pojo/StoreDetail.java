package com.zmsk.smartbox.pojo;

import java.sql.Date;

public class StoreDetail {

    private String detailId;
    private String cellId;
    private String delivererId;
    private String receiverId;
    private String takeCode;
    private Date storeDate;
    private Date takeDate;
    private String state;

    public StoreDetail(String detailId, String cellId, String delivererId, String receiverId,
                       String takeCode, Date storeDate, Date takeDate, String state) {
        this.detailId = detailId;
        this.cellId = cellId;
        this.delivererId = delivererId;
        this.receiverId = receiverId;
        this.takeCode = takeCode;
        this.storeDate = storeDate;
        this.takeDate = takeDate;
        this.state = state;
    }

    public StoreDetail(){}

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(String delivererId) {
        this.delivererId = delivererId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getTakeCode() {
        return takeCode;
    }

    public void setTakeCode(String takeCode) {
        this.takeCode = takeCode;
    }

    public Date getStoreDate() {
        return storeDate;
    }

    public void setStoreDate(Date storeDate) {
        this.storeDate = storeDate;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
