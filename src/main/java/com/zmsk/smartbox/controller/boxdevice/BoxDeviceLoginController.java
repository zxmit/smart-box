package com.zmsk.smartbox.controller.boxdevice;

import com.zmsk.smartbox.common.dto.ResultCode;
import com.zmsk.smartbox.common.dto.ResultServiceDTO;
import com.zmsk.smartbox.pojo.User;
import com.zmsk.smartbox.service.boxdevice.BoxOperateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/boxDevice/login/")
public class BoxDeviceLoginController {

    @Autowired
    private BoxOperateService boxOperateService;

    /**
     * 2
     * @param uuid
     * @return
     */
    @RequestMapping(value = "checkLoginStatus/{uuid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO checkLoginStatus(@PathVariable String uuid) {
        Map<String, String> userLoginState = boxOperateService.checkLoginState(uuid);
        if(userLoginState != null) {
            return ResultServiceDTO.success(userLoginState);
        }
        return new ResultServiceDTO(ResultCode.REQUESTTIMEOUT, "请求超时");
    }

    @RequestMapping(value = "logoutOnBoxDevice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO logoutOnBoxDevice(@Param(value = "userId") String userId, @Param(value = "token") String token) {

        if(!boxOperateService.checkUserState(userId, token)) {
            return new ResultServiceDTO(ResultCode.INVALIDPARAM, "token验证失败");
        }
        return ResultServiceDTO.success();
    }

}
