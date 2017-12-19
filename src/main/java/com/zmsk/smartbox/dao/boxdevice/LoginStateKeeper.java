package com.zmsk.smartbox.dao.boxdevice;


import com.zmsk.smartbox.common.Constants;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LoginStateKeeper {

    private static ConcurrentHashMap<String, Long> QRUuids = null;
    private static ConcurrentHashMap<String, String> qruuidUserMaping = null;
    private static ConcurrentHashMap<String, Long> userAliveTimes = null;
    private static ConcurrentHashMap<String, String> userTokens = null;
    private static Properties props = null;

    static {
        QRUuids = new ConcurrentHashMap<String, Long>();
        userAliveTimes = new ConcurrentHashMap<String, Long>();
        userTokens = new ConcurrentHashMap<String, String>();
        qruuidUserMaping = new ConcurrentHashMap<String, String>();
        props = new Properties();
    }

    /**
     * 检测uuids，删除其中的过期数据
     */
    private static void removeOverTimeUuid() {
        if(QRUuids.size() == 0) return;
        List<String> shouldRemoveKeys = new ArrayList<String>();
        Iterator<String> it = QRUuids.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            long currentTime = System.currentTimeMillis();
            if((currentTime - QRUuids.get(key)) >
                    Long.parseLong(props.getProperty(Constants.BOX_DEVICE_LOGIN_WAITING_TIME,
                            Constants.BOX_DEVICE_LOGIN_WAITING_TIME_DEFAULT))) {
                shouldRemoveKeys.add(key);
            }
        }
        for(String key : shouldRemoveKeys) {
            QRUuids.remove(key);
            qruuidUserMaping.remove(key);
        }
    }

    public static Map<String, String> checkQRCodeUuidExist(String QRUuid) {
        removeOverTimeUuid();
        if(QRUuids.containsKey(QRUuid)) {
            Map<String, String> userState = new HashMap<String, String>();
            String userId = qruuidUserMaping.get(QRUuid);
            String token = userTokens.get(userId);
            userState.put("userId", userId);
            userState.put("token", token);
            return userState;
        }
        return null;
    }

    public static void keepQRCodeUuid(String QRUuid) {
        QRUuids.put(QRUuid, System.currentTimeMillis());
    }

    private static void removeOverTimeUsers() {
        if(userAliveTimes.size() == 0) return;
        List<String> shouldRemoveKeys = new ArrayList<String>();
        Iterator<String> it = userAliveTimes.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            long currentTime = System.currentTimeMillis();
            if((currentTime - userAliveTimes.get(key)) >=
                    Long.parseLong(props.getProperty(Constants.BOX_DEVICE_USER_ALIVE_TIME,
                            Constants.BOX_DEVICE_USER_ALIVE_TIME_DEFAULT))) {
                shouldRemoveKeys.add(key);
            }
        }
        for(String key : shouldRemoveKeys) {
            userAliveTimes.remove(key);
            userTokens.remove(key);
        }
    }

    /**
     * 当用户注册许可后将用户ID信息+设备ID信息存放到一个许可信息队列中
     * 需要写一个定时任务，定时的监控许可队列中每一条许可信息是否超时，如果超时删除该许可信息
     *
     */
    public static boolean checkUserStatus(String userStateKey, String token) {
        if(token == null) return false;
        removeOverTimeUsers();
        if(userAliveTimes.containsKey(userStateKey) && token.equals(userTokens.get(userStateKey))) {
            userAliveTimes.put(userStateKey, System.currentTimeMillis());
            return true;
        }
        return false;
    }

    public static String keepUserState(String uuid, String userId) {
        String token = createTKN();
        userAliveTimes.put(userId, System.currentTimeMillis());
        userTokens.put(userId, token);
        qruuidUserMaping.put(uuid, userId);
        return token;
    }

    private static String createTKN(){
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        token = token.toUpperCase();
        token = token.replaceAll("-", "");
        return token;
    }
}
