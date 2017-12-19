package com.zmsk.smartbox.service.boxdevice.impl;

import com.zmsk.smartbox.common.utils.Md5Util;
import com.zmsk.smartbox.pojo.Cell;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        String str = String.valueOf((int)((Math.random()*9+1)*100000));
        System.out.println(Md5Util.getMD5(str));

    }

}
