package com.example.classdemo.business;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 设备连接数
 */
public class EquipmentConnect {

     public static final String ACCESSDEVICES_URL = "http://edge.cosmoplat.com:30032/company/device/access";//shengchan
    //public static final String ACCESSDEVICES_URL = "http://edge.cosmoplat.com:30152/company/device/access";//test


    public static void main(String[] args) {


        JSONArray ja = new JSONArray();
        JSONObject jb = new JSONObject();
        jb.put("dataName" , "仓位数量" );
        jb.put("mark" , "pCount" );
        jb.put("value" , "24" );
        ja.add(jb);
        jb = new JSONObject();
        jb.put("dataName" , "设备状态" );
        jb.put("mark" , "status" );
        jb.put("value" , "1" );
        ja.add(jb);
        jb = new JSONObject();
        jb.put("dataName" , "可还数量" );
        jb.put("mark" , "availableNum" );
        jb.put("value" , "0" );
        ja.add(jb);
        jb = new JSONObject();
        jb.put("dataName" , "可借数量" );
        jb.put("mark" , "lendNum" );
        jb.put("value" , "24" );
        ja.add(jb);
        jb = new JSONObject();
        jb.put("dataName" , "硬件设备柜id" );
        jb.put("mark" , "etekId" );
        jb.put("value" , RandomUtil.randomStringUpper(12) + RandomUtil.randomNumbers(6));
        ja.add(jb);


        accessDevices(RandomUtil.randomInt(250,270), "深圳市有方科技股份有限公司" , "共享雨伞" , "GXYS" , "MQTT" , "其他" , "低端" ,
                "工业品" , "广东省" , "深圳市" , "10" , ja);

        accessDevices(RandomUtil.randomInt(245,260), "深圳市有方科技股份有限公司" , "共享充电宝" , "GXCDB" , "MQTT" , "其他" , "低端" ,
                "工业品" , "广东省" , "深圳市" , "10" , ja);
    }

    public static void accessDevices(int num, String companyName, String productName, String productCode, String protocol, String classfy, String lever,
                                     String dimension, String province, String city, String worth, JSONArray ja) {
        JSONObject pjb = new JSONObject();
        pjb.put("industryName" , "山东区域" );
        pjb.put("companyName" , companyName);
        pjb.put("productName" , productName);
        pjb.put("productCode" , productCode);
        pjb.put("protocol" , protocol);
        pjb.put("classfy" , classfy);
        pjb.put("lever" , lever);
        pjb.put("dimension" , dimension);

        JSONArray deviceArr = new JSONArray();
        JSONArray deviceDataArr = new JSONArray();
        for (int i = 0; i < num; i++) {
            String deviceCode = "YOUFANGKEJI" + RandomUtil.randomStringUpper(4) + RandomUtil.randomNumbers(6);
            deviceArr.add(makeDevice(deviceCode, productName + deviceCode, province, city, worth));
            ja.forEach(x -> {
                JSONObject jbx = JSONObject.parseObject(x.toString());
                deviceDataArr.add(makeDeviceData(deviceCode, jbx.getString("dataName" ), jbx.getString("mark" ), jbx.getString("value" )));
            });


        }


        pjb.put("device" , deviceArr);
        pjb.put("deviceData" , deviceDataArr);

        String rel = HttpUtil.post(ACCESSDEVICES_URL, pjb.toString());

        System.out.println(pjb.toString());
        System.out.println(rel);
    }


    public static JSONObject makeDevice(String deviceCode, String name, String province, String city, String worth) {
        JSONObject jb = new JSONObject();
        jb.put("deviceCode" , deviceCode);
        jb.put("name" , name);
        jb.put("province" , province);
        jb.put("city" , city);
        jb.put("address" , "" );
        jb.put("worth" , worth);
        return jb;
    }

    public static JSONObject makeDeviceData(String deviceCode, String dataName, String mark, String value) {
        JSONObject jb = new JSONObject();
        jb.put("deviceCode" , deviceCode);
        jb.put("name" , dataName);
        jb.put("mark" , mark);
        jb.put("value" , value);
        jb.put("timestamp" , LocalDateTime.now().toInstant(ZoneOffset.of("+8" )).toEpochMilli() + "" );
        return jb;
    }


}
