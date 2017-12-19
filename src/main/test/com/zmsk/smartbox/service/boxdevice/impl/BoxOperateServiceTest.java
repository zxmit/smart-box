package com.zmsk.smartbox.service.boxdevice.impl;


import com.zmsk.smartbox.pojo.CellCount;
import com.zmsk.smartbox.pojo.User;
import com.zmsk.smartbox.service.boxdevice.BoxOperateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class BoxOperateServiceTest {

    @Resource
    private BoxOperateService boxOperateService;

    public void keepQRCodeUuidTest() {
        boxOperateService.keepUserState("2345","1");
    }

    @Test
    public void getUserInfoTeset() {
        User user = boxOperateService.getUserInfo("wx0001");
        System.out.println(user);
    }

    @Test
    public void getAvailableCellsTest() {
        List<CellCount> list = boxOperateService.getAvailableCells("1");
        System.out.println(list);
    }





}
