package com.zmsk.smartbox.pojo;

public class User {

    private String userId;
    private String wxId;
    private String telNum;
    private String state;
    private String statement;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", wxId='" + wxId + '\'' +
                ", telNum='" + telNum + '\'' +
                ", state='" + state + '\'' +
                ", statement='" + statement + '\'' +
                '}';
    }
}
