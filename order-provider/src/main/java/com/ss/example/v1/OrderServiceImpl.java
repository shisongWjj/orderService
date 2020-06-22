package com.ss.example.v1;

/**
 * OrderServiceImpl
 *
 * @author shisong
 * @date 2020/6/22
 */
public class OrderServiceImpl implements OrderService{
    public String queryList() {
        return "EXECUTE QUERY LIST METHOD";
    }

    public String queryInfoById(String id) {
        return "EXECUTE QUERY INFO BY ID METHOD";
    }
}
