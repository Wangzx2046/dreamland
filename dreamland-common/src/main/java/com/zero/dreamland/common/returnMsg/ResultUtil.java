package com.zero.dreamland.common.returnMsg;



public class ResultUtil {


    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success("操作成功！");
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result failed() {
        return error(null,"操作失败！");
    }

    public static Result error(ResultEnum re) {
        return error(re.getCode(),re.getMsg());
    }
    public static Result error(Integer code) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(EnumUtil.getByCode(code,ResultEnum.class));
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
