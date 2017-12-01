package com.tony.netty.rpc.core.client;

import com.tony.netty.rpc.core.server.RpcServerLoader;
import com.tony.netty.rpc.core.model.MessageRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Author jiangwj20966 on 2017/11/22.
 */
public class MessageSendProxy<T> implements InvocationHandler {

    private Class<T> cls;

    public MessageSendProxy(Class<T> cls) {
        this.cls = cls;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setTypeParameters(method.getParameterTypes());
        request.setParameters(args);

        MessageSendHandler handler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack callBack = handler.sendRequest(request);
        return callBack.start();
    }
}
