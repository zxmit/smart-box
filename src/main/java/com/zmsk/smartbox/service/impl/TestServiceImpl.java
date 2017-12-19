package com.zmsk.smartbox.service.impl;

import com.zmsk.smartbox.mapper.TestMapper;
import com.zmsk.smartbox.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestServiceImpl implements com.zmsk.smartbox.service.TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public Test queryTestData(int id) {
        return testMapper.selectByPrimaryKey(id);
    }
}
