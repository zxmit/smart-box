package com.zmsk.smartbox.service.boxdevice;

import com.zmsk.smartbox.pojo.Cell;
import com.zmsk.smartbox.pojo.CellCount;
import com.zmsk.smartbox.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface BoxOperateService {

    /**
     *
     * @param QRCodeUuid
     */
    public void keepQRCodeUuid(String QRCodeUuid);

    /**
     *
     * @param uuid
     * @return
     */
    public Map<String, String> checkLoginState(String uuid);

    /**
     *
     * @param uuid
     * @param userId
     * @return
     */
    public String keepUserState(String uuid, String userId);

    /**
     *
     * @param userId
     * @param token
     * @return
     */
    public boolean checkUserState(String userId, String token);

    /**
     * 查询用户信息
     * @param wxId
     * @return
     */
    public User getUserInfo(String wxId);

    public List<CellCount> getAvailableCells(String boxId);

    public Cell holdCellId(String boxId, String typeId);

    public Map<String, Object> saveStoreInfo(String boxId, Cell cell, String receiverTelephone, String userId);

    public boolean checkStoreItemUser(String userId, String storeDetailId);

    public void cancelStoreItem(String storeDetailId);

    public String queryStoredItemByTakeCode(String boxId, String codeId);

    public List<String> queryStoredItemByUserId(String boxId, String receiverId);

    public boolean checkCellReceiver(String boxId, String cellNum, String userId);

    public boolean openCell(String boxId, String cellNum);

    public boolean isCellClose(String boxId, String cellNum);

    public boolean isUserRegister(String boxId, String takeCode);
}
