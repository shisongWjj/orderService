package com.ss.example.v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RpcServer
 *
 * @author shisong
 * @date 2020/6/22
 */
public class RpcServer {

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(Object service,int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                executorService.submit(new ProcessorHandler(socket,service));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
