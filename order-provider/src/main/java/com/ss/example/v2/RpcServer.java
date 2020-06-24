package com.ss.example.v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RpcServer
 *
 * @author shisong
 * @date 2020/6/24
 */
public class RpcServer {

    ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 发布服务，这里我们需要知道 要发布的服务和端口号
     * @param service 服务
     * @param port 端口号
     */
    public void publisher(Object service,int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                //处理连接  利用多线程
                executorService.submit(new ProcessorHandler(service,socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
