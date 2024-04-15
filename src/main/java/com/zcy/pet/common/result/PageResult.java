package com.zcy.pet.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private Data<T> data;
    private Boolean success;

    @lombok.Data
    public static class Data<T> {
        private List<T> list;
        private long total;
    }

    public static <T> PageResult<T> success(IPage<T> page) {
        PageResult<T> result = new PageResult<>();
        Data<T> data = new Data<>();
        data.setList(page.getRecords());
        data.setTotal(page.getTotal());
        // 设置result的值
        result.setData(data);
        return result;
    }
}
