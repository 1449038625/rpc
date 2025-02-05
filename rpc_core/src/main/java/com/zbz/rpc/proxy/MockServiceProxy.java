package com.zbz.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Classname: MockServiceProxy
 * Package: com.zbz.rpc.proxy
 * Decription:mock服务代理类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 22:59
 * @Version: v1.0
 */
public class MockServiceProxy implements InvocationHandler {
    /**
     * 调用代理方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return getDefaultObject(method.getReturnType());
    }

    private Object getDefaultObject(Class<?> type){
        if (type.isPrimitive()){
            if(type == int.class){
                return 0;
            }else if(type == long.class){
                return 0L;
            }else if(type == float.class){
                return 0F;
            }else if(type == double.class){
                return 0D;
            }else if(type == boolean.class){
                return false;
            }else if(type == char.class){
                return '\u0000';
            }else if(type == byte.class){
                return (byte)0;
            }else if(type ==short.class){
                return (short)0;
            }
        }
        return null;
    }


}
