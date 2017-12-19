package com.zmsk.smartbox.mapper;

import com.zmsk.smartbox.pojo.StoreDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetailMapper {

    public int insertStoreDetail(StoreDetail storeDetail);

    public int isTakeCodeExist(@Param("boxId") String boxId, @Param("takeCode") String takeCode);

    public int updateStoreDetail(StoreDetail storeDetail);

    public String queryStoredItemByTakeCode(@Param("boxId") String boxId, @Param("takeCode") String takeCode);

    public List<String> queryStoredItemByUserId(@Param("boxId") String boxId, @Param("receiverId") String receiverId);

    public List<String> queryCurrentStoredItemByUserId(@Param("boxId") String boxId,
                                                       @Param("cellNum") String cellNum,
                                                       @Param("receiverId") String receiverId);

    public int changeDetailState(@Param("cellId") String cellId,
                                 @Param("currentState") String currentState,
                                 @Param("newState") String newState);

    public StoreDetail queryStoredItem(@Param("delivererId")String delivererId, @Param("detailId")String detailId);

    public String queryCellId(StoreDetail storeDetail);

    public String queryUserStateByTakeCode(@Param("boxId") String boxId, @Param("takeCode") String takeCode);

}
