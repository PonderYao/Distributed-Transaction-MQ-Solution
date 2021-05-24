package com.transaction.distributed.base;

import lombok.Data;

/**
 * ResponseBase.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 21:24
 */
@Data
public class ResponseBase {

    private Integer rtnCode;
    private String msg;
    private Object data;

    public ResponseBase() {

    }

    ResponseBase(Integer rtnCode, String msg, Object data) {
        super();
        this.rtnCode = rtnCode;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBase [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
    }

}
