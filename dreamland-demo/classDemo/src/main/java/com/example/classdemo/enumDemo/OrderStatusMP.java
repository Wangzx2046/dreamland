//package com.example.classdemo.enumDemo;
//
//import com.baomidou.mybatisplus.annotation.IEnum;
//import com.fasterxml.jackson.annotation.JsonValue;
//import lombok.Getter;
//
//@Getter
//public enum OrderStatusMP implements IEnum<String> {
//
//    //10未派单、 00已派单、01已接单、02已签到、03已入厂、04已过磅、05待装中、06装车中、07已过重、08出厂、09已签收   90未接单取消 91已接单取消 92 提交取消申请 93 停产取消
//    ORDER_NEW("10","未派单"),
//    ORDER_DISTRIBUTION("00","已派单"),
//    ORDER_ACCEPT("01","已接单"),
//    ORDER_SIGNIN("02","已签到"),
//    ORDER_IN("03","已入厂"),
//    ORDER_CARW("04","已过磅"),
//    ORDER_WAIT("05","待装中"),
//    ORDER_WORK("06","装车中"),
//    ORDER_LOADW("07","已过重"),
//    ORDER_OUT("08","出厂"),
//    ORDER_SIGN("09","已签收"),
//    ORDER_AOUT_CANCEL("90","未接单取消"),
//    ORDER_HAND_CANCEL("91","已接单取消"),
//    ORDER_CANCEL_APPLY("92","提交取消申请"),
//    STOP_PROD_CANCEL("93","停产取消");
//
//    private final String statusCode;
//    private final String statusName;
//
//    OrderStatusMP(String statusCode, String statusName) {
//        this.statusCode = statusCode;
//        this.statusName = statusName;
//    }
//
//    @Override
//    public String getValue() {
//        return this.statusCode;
//    }
//
//    //想返回汉字，需要重写toString
//    @Override
//    public String toString() {
//        return this.statusName;
//    }
//
//    @JsonValue
//    public String getName() {
//        return statusName;
//    }
//
//
//}
