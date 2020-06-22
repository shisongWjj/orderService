package com.ss.example.v1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * ProcessorHandler
 *
 * @author shisong
 * @date 2020/6/22
 */
public class ProcessorHandler implements Runnable{

    private Socket socket;

    private  Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    public void run() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            Object rs = this.invoke(rpcRequest,service);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rs);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Object invoke(RpcRequest rpcRequest, Object service) throws Exception{
        Class<?> cls = Class.forName(rpcRequest.getClassName());
        Method method = cls.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
        return method.invoke(service,rpcRequest.getArgs());
    }
}
