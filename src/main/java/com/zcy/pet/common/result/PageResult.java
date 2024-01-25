package com.zcy.pet.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private int code;
    private Data<T> data;
    private String msg;

    @lombok.Data
    public static class Data<T> {
        private List<T> list;
        private long total;
    }

    public static <T> PageResult<T> success(IPage<T> page) {
        PageResult<T> result = new PageResult<>();
        Data<T> data = new Data<T>();
        data.setList(page.getRecords());
        data.setTotal(page.getTotal());
        // 设置result的值
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData(data);
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }
}
