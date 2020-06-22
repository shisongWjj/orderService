package com.ss.example.v1;

/**
 * BootStrap
 *
 * @author shisong
 * @date 2020/6/22
 */
public class BootStrap {

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(orderService,8080);
    }

}
