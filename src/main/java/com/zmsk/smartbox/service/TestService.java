package com.zmsk.smartbox.service;

import com.zmsk.smartbox.pojo.Test;

public interface TestService {

    /**
     * 查询测试数据
     *
     * @param id
     *          测试数据Id
     * @return
     */
    public Test queryTestData(int id);
}
