package com.zmsk.smartbox.controller.client;

import com.zmsk.smartbox.common.dto.ResultCode;
import com.zmsk.smartbox.common.dto.ResultServiceDTO;
import com.zmsk.smartbox.pojo.User;
import com.zmsk.smartbox.service.boxdevice.BoxOperateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client/login/")
public class UserClientLoginController {

    @Autowired
    private BoxOperateService boxOperateService;

    @RequestMapping(value = "loginOnBoxDevice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO loginOnBoxDevice(@Param(value = "wxId") String wxId,
                                             @Param(value = "uuid") String uuid) {

        User user = boxOperateService.getUserInfo(wxId);

        if(user == null) {
            return new ResultServiceDTO(ResultCode.UNREGISTER, "未注册");
        }

        if(!"1".equals(user.getState())) {
            return new ResultServiceDTO(ResultCode.INBLACKLIST, "该用户已被拉入黑名单");
        }

        String token = boxOperateService.keepUserState(uuid, user.getUserId());
        boxOperateService.keepQRCodeUuid(uuid);

        Map<String, String> result = new HashMap<String, String>();
        result.put("userId", user.getUserId());
        result.put("token", token);

        return ResultServiceDTO.success(result);
    }
}
