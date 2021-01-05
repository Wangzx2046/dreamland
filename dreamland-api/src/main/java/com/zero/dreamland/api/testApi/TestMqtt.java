package com.zero.dreamland.api.testApi;

import com.zero.dreamland.common.annotation.AnonymousAccess;
import com.zero.dreamland.mqtt.biz.MqttPushClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lxw
 * @Date: 2018/10/19 19:36
 * @email: 1229703575@qq.com
 * @Description: 测试文件
 */
@RestController
@RequestMapping("openApi/pub")
@Api("测试")
public class TestMqtt {

    @Autowired
    private MqttPushClient mqttPushClient;

    @AnonymousAccess
    @ApiOperation(value = "发布主题", notes = "测试发布主题")
    @GetMapping(value = "/publishTopic")
    public ResponseEntity<Object> publishTopic() {
        mqttPushClient.publish(0,false,"test/test","测试一下发布消息test/test");

        mqttPushClient.publish(0,false,"test/haier","测试一下发布消息test-haier");
        mqttPushClient.publish(0,false,"test/jbinfo","测试一下发布消息haier-test");
        mqttPushClient.publish(0,false,"haier","测试一下发布消息haier");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

