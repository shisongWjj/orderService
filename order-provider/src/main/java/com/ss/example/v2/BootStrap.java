package com.ss.example.v2;

import com.ss.example.v1.OrderService;

/**
 * BootStrap
 *
 * @author shisong
 * @date 2020/6/24
 */
public class BootStrap {

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(orderService,8080);
    }

}
