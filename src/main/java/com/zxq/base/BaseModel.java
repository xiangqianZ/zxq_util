package com.zxq.base;

import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
public class BaseModel {

    public transient Integer pageNum;
    public transient Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
