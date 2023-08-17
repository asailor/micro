package com.gdsig.system.entity;

import lombok.Data;

/**
 * @author : xs
 * @date : 2023-08-17 11:04
 **/

@Data
public class RequestData<T> {

    private Header header;

    private T body;

    @Data
    public class Header {
        private String token;
    }

    @Data
    public class Order {
        String orderNo;
    }
}
