package com.ss.example.v2;

import com.ss.example.v1.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * ProcessorHandler
 *
 * @author shisong
 * @date 2020/6/24
 */
public class ProcessorHandler implements Runnable{

    private Object service;

    private Socket socket;


    public ProcessorHandler(Object service, Socket socket) {
        this.service = service;
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream( socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest)ois.readObject();
            Object rs = this.invoke(rpcRequest,service);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rs);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Object invoke(RpcRequest rpcRequest, Object service) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> cls = Class.forName(rpcRequest.getClassName());
        Method method = cls.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
        return method.invoke(service,rpcRequest.getArgs());
    }
}
