package com.zmsk.smartbox.controller;

import com.zmsk.smartbox.common.dto.ResultCode;
import com.zmsk.smartbox.common.dto.ResultServiceDTO;
import com.zmsk.smartbox.pojo.Test;
import com.zmsk.smartbox.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test/")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value = "data/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultServiceDTO queryTestData(@PathVariable(value = "id") String id) {

        logger.info("param: " + id);

        if (StringUtils.isEmpty(id)) {
            return new ResultServiceDTO(ResultCode.INVALIDPARAM, "Invalid storeId is null ");
        }

        Test notice = testService.queryTestData(Integer.parseInt(id));

        return ResultServiceDTO.success(notice);
    }

}
