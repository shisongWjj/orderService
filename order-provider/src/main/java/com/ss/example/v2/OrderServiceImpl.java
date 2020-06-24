package com.ss.example.v2;

import com.ss.example.v1.OrderService;

/**
 * OrderServiceImpl
 *
 * @author shisong
 * @date 2020/6/24
 */
public class OrderServiceImpl implements OrderService {
    public String queryList() {
        return "EXECUTE QUERY LIST METHOD V2";
    }

    public String queryInfoById(String id) {
        return "EXECUTE QUERY INFO BY ID METHOD V2";
    }
}
