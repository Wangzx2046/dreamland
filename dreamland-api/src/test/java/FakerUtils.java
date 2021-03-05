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

     //   accessDevices();
    }

    public static void accessDevices() {


        JSONObject pjb = new JSONObject();
        pjb.put("industryName", "山东区域");
        pjb.put("companyName", "鲁中耐火材料有限公司");
        pjb.put("productName", "螺旋压机");
        pjb.put("productCode", "LUOXUANYAJI");
        pjb.put("protocol", "MQTT");
        pjb.put("classfy", "焙烧/煅烧/烧结设备");
        pjb.put("lever", "高端");
        pjb.put("dimension", "工业品");

        JSONArray deviceArr = new JSONArray();
        JSONArray deviceDataArr = new JSONArray();
        for (int i = 0; i < 1000; i++) {

            String deviceCode = "YFSGW0D0J0"+RandomUtil.randomStringUpper(4) + RandomUtil.randomNumbers(6);
            deviceArr.add(makeDevice(deviceCode));
            deviceDataArr.add(makeDeviceData(deviceCode));
        }


        pjb.put("device", deviceArr);
        pjb.put("deviceData", deviceDataArr);


        String rel = HttpUtil.post(ACCESSDEVICES_URL, pjb.toString());

        System.out.println(pjb.toString());
        System.out.println(rel);
    }


    public static JSONObject makeDevice(String deviceCode) {
        JSONObject jb = new JSONObject();
        jb.put("deviceCode", deviceCode);
        jb.put("name", "螺旋压机4号");
        jb.put("province", "山东省");
        jb.put("city", "淄博市");
        jb.put("address", "淄川区罗村镇聂村");
        jb.put("worth", "100");
        return jb;
    }

    public static JSONObject makeDeviceData(String deviceCode) {
        JSONObject jb = new JSONObject();
        jb.put("deviceCode", deviceCode);
        jb.put("name", "实时温度");
        jb.put("mark", "temp");
        jb.put("value", RandomUtil.randomInt(30, 50) + "");
        jb.put("timestamp", LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() + "");
        return jb;
    }


}
