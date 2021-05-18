import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 造假工具类
 */
public class FakerUtils {

    public static final String ACCESSDEVICES_URL = "http://edge.cosmoplat.com:30032/company/device/access";


    public static void main(String[] args) {

        accessDevices(10, "深圳市有方科技股份有限公司" , "共享雨伞" , "GXYS" , "MQTT" , "共享设备" , "中端" ,
                "消费品" , "广东省" , "深圳市" , "10" , "设备状态" , "status" , "1" );

        accessDevices(10, "深圳市有方科技股份有限公司" , "共享充电宝" , "GXCDB" , "MQTT" , "共享设备" , "中端" ,
                "消费品" , "广东省" , "深圳市" , "10" , "仓位数量" , "availableNum" , "24" );
    }

    public static void accessDevices(int num, String companyName, String productName, String productCode, String protocol, String classfy, String lever,
                                     String dimension, String province, String city, String worth, String dataName, String mark, String value) {
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
            deviceDataArr.add(makeDeviceData(deviceCode, dataName, mark, value));
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
