package com.spirit.teresa.server;

import com.spirit.teresa.codec.IoPacket;
import com.spirit.teresa.serializer.Serializer;

import java.lang.reflect.Method;

public class MethodHanler {
    private Object object;
    private Method method;
    private Object subCmd;
    private Class[] parameterType;
    private Class returnType;

    public MethodHanler(Object object, Method method, Object subCmd, Class[] parameterType, Class returnType) {
        this.object = object;
        this.method = method;
        this.subCmd = subCmd;
        this.parameterType = parameterType;
        this.returnType = returnType;
    }

    public Object invoke(IoPacket packet, Serializer serializer) throws Exception {
        Object req = packet.getContent(parameterType[0],serializer);
        return method.invoke(object,req,packet);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getSubCmd() {
        return subCmd;
    }

    public void setSubCmd(Object subCmd) {
        this.subCmd = subCmd;
    }


    @Override
    public String toString() {
        return "MethodHanler{" +
                "object=" + object +
                ", method=" + method +
                ", subCmd=" + subCmd +
                ", parameterType=" + parameterType +
                ", returnType=" + returnType +
                '}';
    }
}