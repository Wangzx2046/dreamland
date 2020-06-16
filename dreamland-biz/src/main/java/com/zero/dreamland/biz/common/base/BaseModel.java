package com.zero.dreamland.biz.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: BaseModel
 * @Description: 基础模型表
 * @Author: wzx
 * @Date: 2019-04-12 15:50
 * @Version 1.0.0
 **/
@Data
public class BaseModel implements Serializable {
    @ApiModelProperty(value = "主键ID")
    @Length(max = 32, message = "主键id长度不能超过32")
    @NotBlank(message = "id should not be empty", groups = {UpdateGroup.class})
    @Null(message = "id should  be empty", groups = {AddGroup.class})
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;//主键ID


    @ApiModelProperty(value = "状态标志：0正常 1禁用 2删除")
    @TableLogic
    private Integer delFlag;//状态标志：0正常 1禁用 2删除

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;//插入时间

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;//插入者

    @ApiModelProperty(value = "更新人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;//更新者

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;//更新时间
}