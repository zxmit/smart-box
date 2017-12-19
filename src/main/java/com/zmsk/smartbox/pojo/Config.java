package com.zmsk.smartbox.pojo;

import com.zmsk.smartbox.common.Constants;

import java.util.Properties;

public class Config {

    private long boxDeviceUserAliveTime;
    private long boxDeviceLoginWaitingTime;

    public void setProps(Properties props) {
        this.boxDeviceUserAliveTime = Long.parseLong(props.getProperty(Constants.BOX_DEVICE_USER_ALIVE_TIME,
                Constants.BOX_DEVICE_USER_ALIVE_TIME_DEFAULT));
        this.boxDeviceLoginWaitingTime = Long.parseLong(props.getProperty(Constants.BOX_DEVICE_LOGIN_WAITING_TIME,
                Constants.BOX_DEVICE_LOGIN_WAITING_TIME_DEFAULT));
    }

    public long getBoxDeviceUserAliveTime() {
        return boxDeviceUserAliveTime;
    }

    public long getBoxDeviceLoginWaitingTime() {
        return boxDeviceLoginWaitingTime;
    }
}
