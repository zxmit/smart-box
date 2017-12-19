package com.zmsk.smartbox.controller.boxdevice;

import com.zmsk.smartbox.common.dto.ResultCode;
import com.zmsk.smartbox.pojo.Cell;
import com.zmsk.smartbox.pojo.CellCount;
import com.zmsk.smartbox.service.boxdevice.BoxOperateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zmsk.smartbox.common.dto.ResultServiceDTO;

import java.util.*;

@Controller
@RequestMapping("/boxDevice/store/")
public class ItemStoreController {

    @Autowired
    private BoxOperateService boxOperateService;

    /**
     *
     * @param boxId
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value = "queryAvailableCells/{boxId}/{userId}/{token}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO queryAvailableCells(@PathVariable String boxId,@PathVariable String userId,
                                                @PathVariable String token) {
        if(!boxOperateService.checkUserState(userId, token)) {
            return new ResultServiceDTO(ResultCode.UNAUTH, "token验证失败");
        }
        List<CellCount> availableCellNumber = boxOperateService.getAvailableCells(boxId);

        return ResultServiceDTO.success(availableCellNumber);
    }

    /**
     *
     * @param boxId
     * @param typeId
     * @param receiverTelephone
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value = "openBoxCell", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO openBoxCell(@Param(value = "boxId") String boxId, @Param(value = "typeId") String typeId,
                                        @Param(value = "receiverTelephone") String receiverTelephone,
                                        @Param(value = "userId") String userId, @Param(value = "token") String token) {
        if(!boxOperateService.checkUserState(userId, token)) {
            return new ResultServiceDTO(ResultCode.UNAUTH, "请重新扫码登录");
        }

        Cell cell = boxOperateService.holdCellId(boxId, typeId);
        if(cell == null) {
            return new ResultServiceDTO(ResultCode.UNAVAIALABLECELL, "该种类型的格子无空余");
        }

        Map<String, Object> result = boxOperateService.saveStoreInfo(boxId, cell, receiverTelephone, userId);
        if(result == null) {
            return new ResultServiceDTO(ResultCode.OPENBOXFAIL, "开箱失败");
        }
        return ResultServiceDTO.success(result);
    }



    /**
     *
     * @param storeDetailId
     * @return
     */
    @RequestMapping(value = "cancelStore", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO cancelStore(@Param(value = "storeDetailId") String storeDetailId,
                                          @Param(value = "userId") String userId, @Param(value = "token") String token) {
        if(!boxOperateService.checkUserState(userId, token)) {
            return new ResultServiceDTO(ResultCode.UNAUTH, "请重新扫码登录");
        }
        boolean checker = boxOperateService.checkStoreItemUser(storeDetailId, userId);
        if(!checker) {
            return new ResultServiceDTO(ResultCode.UNAUTH, "无法取消存储，无此权限");
        }
        boxOperateService.cancelStoreItem(storeDetailId);

        return ResultServiceDTO.success();
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "isCellsClosed/{boxId}/{cellId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO isCellClosed(@PathVariable(value = "boxId") String boxId, @PathVariable(value = "cellId") String cellId) {

        Map<String, List<String>> result = new HashMap<String, List<String>>();
        List<String> cellNum = new ArrayList<String>();
        result.put("cellNum", cellNum);

        if(true) {
            return new ResultServiceDTO(ResultCode.CELLUNCLOSED, "仓门未关闭", result);
        }

        return ResultServiceDTO.success();
    }

}
