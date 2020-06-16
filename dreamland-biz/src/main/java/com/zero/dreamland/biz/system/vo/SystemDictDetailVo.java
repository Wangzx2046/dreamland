package com.zero.dreamland.biz.system.vo;

import com.zero.dreamland.biz.system.entity.SystemDictDetail;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author : Wang.zx
 * @Description ：数据字典详情
 * @since : 2020-04-07
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemDictDetail对象", description = "数据字典详情")
public class SystemDictDetailVo extends SystemDictDetail {

    private static final long serialVersionUID = 1L;


    private String name;



}
