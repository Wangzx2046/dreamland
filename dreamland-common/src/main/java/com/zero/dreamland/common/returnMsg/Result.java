package com.zero.dreamland.common.returnMsg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description：通用返回类
 * @Creation Date    ：2019/4/1915:19
 * @Author           ：wzx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;


}
