package com.zmsk.smartbox.service.boxdevice.impl;

import com.zmsk.smartbox.common.utils.Md5Util;
import com.zmsk.smartbox.common.utils.PrimaryKeyUtil;
import com.zmsk.smartbox.dao.boxdevice.LoginStateKeeper;
import com.zmsk.smartbox.mapper.CellMapper;
import com.zmsk.smartbox.mapper.DetailMapper;
import com.zmsk.smartbox.mapper.UserMapper;
import com.zmsk.smartbox.pojo.*;
import com.zmsk.smartbox.service.boxdevice.BoxOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BoxOperateServiceImpl implements BoxOperateService {

    @Autowired
    private Config config;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CellMapper cellMapper;

    @Autowired
    private DetailMapper detailMapper;

    @Override
    public void keepQRCodeUuid(String QRCodeUuid) {
        LoginStateKeeper.keepQRCodeUuid(QRCodeUuid);
    }

    @Override
    public Map<String, String> checkLoginState(String uuid) {
        long startTime = System.currentTimeMillis();
        while(true) {
            LoginStateKeeper.checkQRCodeUuidExist(uuid);
            Map<String, String> userState = LoginStateKeeper.checkQRCodeUuidExist(uuid);
            if(userState != null) {
                return userState;
            }
            try {
                long currentTime = System.currentTimeMillis();
                if((currentTime - startTime) > config.getBoxDeviceLoginWaitingTime()) {
                    break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
        return null;
    }

    @Override
    public String keepUserState(String uuid, String userId) {
        return LoginStateKeeper.keepUserState(uuid, userId);
    }

    @Override
    public boolean checkUserState(String userId, String token) {
        return LoginStateKeeper.checkUserStatus(userId, token);
    }

    @Override
    public User getUserInfo(String wxId) {
        if(wxId == null) return null;
        User user = userMapper.selectByWxId(wxId);
        return user;
    }

    @Override
    public List<CellCount> getAvailableCells(String boxId) {
        if(boxId == null) return null;
        List<CellCount> counts = cellMapper.queryAvailableCount(boxId);
        return counts;
    }

    @Override
    public Cell holdCellId(String boxId, String typeId) {
        Cell cell = null;
        synchronized (this) {
            // 查询空余格子
            cell= cellMapper.queryRandomAvailableCell(boxId, typeId);
            if(cell == null) {
                return null;
            }

            int resultNum = cellMapper.holdCell(cell.getCellId());
            if(resultNum == 0) {
                return null;
            }
        }
        return cell;
    }

    @Override
    public Map<String, Object> saveStoreInfo(String boxId, Cell cell,
                                             String receiverTelephone, String userId) {

        Map<String, Object> result = new HashMap<String, Object>();
        String detailId = PrimaryKeyUtil.generateUniqueKey();
        String delivererId = userId;
        String receiverId = getReceiverId(userId, receiverTelephone);
        String takeCode = getCode(boxId);
        System.out.println("==========>> takeCode " + takeCode);
        Date storeDate = new Date(System.currentTimeMillis());
        StoreDetail storeDetail = new StoreDetail(detailId, cell.getCellId(), delivererId, receiverId,
                takeCode, storeDate, null, null);
        detailMapper.insertStoreDetail(storeDetail);
        result.put("storeDetailId", detailId);
        result.put("cellNum", cell.getCellNum());
        return result;
    }

    @Override
    public void cancelStoreItem(String storeDetailId) {
        StoreDetail storeDetail = new StoreDetail();
        storeDetail.setDetailId(storeDetailId);
        storeDetail.setState("4");
        String cellId = detailMapper.queryCellId(storeDetail);
        cellMapper.releaseCell(cellId);
        detailMapper.updateStoreDetail(storeDetail);
    }

    @Override
    public boolean checkStoreItemUser(String userId, String storeDetailId) {
        StoreDetail detail = detailMapper.queryStoredItem(userId, storeDetailId);
        if(detail != null) {
            return true;
        }
        return false;
    }

    @Override
    public String queryStoredItemByTakeCode(String boxId, String takeCode) {

        return detailMapper.queryStoredItemByTakeCode(boxId, takeCode);

    }

    @Override
    public List<String> queryStoredItemByUserId(String boxId, String userId) {

        List<String> cellNums = detailMapper.queryStoredItemByUserId(boxId, userId);
        return cellNums;
    }


    @Override
    public boolean checkCellReceiver(String boxId, String cellNum, String userId) {
        List<String> results = detailMapper.queryCurrentStoredItemByUserId(boxId, cellNum, userId);
        if(results==null || results.size()==0)
            return false;
        return true;
    }

    @Override
    public boolean openCell(String boxId, String cellNum) {
        String cellId = cellMapper.queryCellId(boxId, cellNum);
        StoreDetail storeDetail = new StoreDetail();
        storeDetail.setCellId(cellId);
        storeDetail.setState("2");
        detailMapper.changeDetailState(cellId, "1", "2");
        cellMapper.releaseCell(cellId);
        // 开锁
        return true;
    }

    @Override
    public boolean isCellClose(String boxId, String cellNum) {

        // 检查锁是否关闭

        return false;
    }

    @Override
    public boolean isUserRegister(String boxId, String takeCode) {
        String state = detailMapper.queryUserStateByTakeCode(boxId, takeCode);
        if("1".equals(state)) {
            return true;
        }
        return false;
    }

    private String getReceiverId(String userId, String phoneNumber) {

        if(phoneNumber == null || "".equals(phoneNumber.trim())) {
            return userId;
        }
        User user = userMapper.selectByPhoneNumber(phoneNumber);
        if(user == null) {
            String key = PrimaryKeyUtil.generateUniqueKey();
            user = new User();
            user.setUserId(key);
            user.setTelNum(phoneNumber);
            userMapper.addUserWithPhoneNumber(user);
        }
        return user.getUserId();
    }

    /**
     * 获取取件码
     * @param boxId
     * @return
     */
    private String getCode(String boxId) {
        while(true) {
            String randomCode = String.valueOf((int)((Math.random()*9+1)*100000));
            String code = Md5Util.getMD5(randomCode);
            int result = detailMapper.isTakeCodeExist(boxId, code);
            if(result == 0) {
                return code;
            }
        }
    }




}
