package com.zmsk.smartbox.controller.boxdevice;

import com.zmsk.smartbox.common.dto.ResultCode;
import com.zmsk.smartbox.common.dto.ResultServiceDTO;
import com.zmsk.smartbox.service.boxdevice.BoxOperateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/boxDevice/fetch/")
public class ItemFetchController {

    @Autowired
    private BoxOperateService boxOperateService;

    @RequestMapping(value = "queryStoredItems/{boxId}/{userId}/{token}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO queryStoredItems(@PathVariable String boxId, @PathVariable String userId,
                                             @PathVariable String token) {

        if(!boxOperateService.checkUserState(userId, token)) {
            return new ResultServiceDTO(ResultCode.UNAUTH, "token验证失败");
        }

        List<String> cells = boxOperateService.queryStoredItemByUserId(boxId, userId);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("cellNums", cells);
        return ResultServiceDTO.success(maps);
    }

    @RequestMapping(value = "openCellDoor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO openCellDoor(@Param(value = "boxId") String boxId, @Param(value = "cellNum") String cellNum,
                                         @Param(value = "userId") String userId, @Param(value = "tokenId") String token) {
        if(!boxOperateService.checkUserState(userId, token)) {
            return new ResultServiceDTO(ResultCode.UNAUTH, "token验证失败");
        }

        // 验证cellId与用户是否匹配
        boolean checker = boxOperateService.checkCellReceiver(boxId, cellNum, userId);
        if(!checker) {
            return new ResultServiceDTO(ResultCode.OPENBOXFAIL, "没有开此格子的权限");
        }

        boolean status = boxOperateService.openCell(boxId, cellNum);
        if(!status) {
           return new ResultServiceDTO(ResultCode.OPENBOXFAIL, "仓门打开失败");
        }
        return ResultServiceDTO.success();
    }

    @RequestMapping(value = "openCellDoor/{boxId}/{takeCode}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO openCellDoorByTakeCode(@PathVariable(value = "boxId") String boxId,@PathVariable(value = "takeCode") String takeCode) {

        // 查询用户是否注册
        boolean isRegister = boxOperateService.isUserRegister(boxId, takeCode);
        if(!isRegister) {
            return new ResultServiceDTO(ResultCode.UNREGISTER, "用户未注册");
        }

        String cellNum = boxOperateService.queryStoredItemByTakeCode(boxId, takeCode);
        if(cellNum == null) {
            return new ResultServiceDTO(ResultCode.OPENBOXFAIL, "取件码错误");
        }
        boolean status = boxOperateService.openCell(boxId, cellNum);
        if(!status) {
            return new ResultServiceDTO(ResultCode.OPENBOXFAIL, "仓门打开失败");
        }

        return ResultServiceDTO.success();
    }

    @RequestMapping(value = "isCellsClosed/{boxId}/{cellId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO isCellsClosed(@PathVariable(value = "boxId") String boxId, @PathVariable(value = "cellId") String cellId) {

        if(true) {
            return new ResultServiceDTO(ResultCode.CELLUNCLOSED, "仓门未关闭");
        }
        return ResultServiceDTO.success();
    }






}
