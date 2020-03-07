// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from nrpc.proto

package com.spirit.teresa.packet;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import com.dyuproject.protostuff.ByteString;
import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;
import com.dyuproject.protostuff.Schema;
import com.spirit.teresa.codec.AbstractIoPacket;
import com.spirit.teresa.codec.IoPacket;
import com.spirit.teresa.serializer.Serializer;
import com.spirit.teresa.utils.U;

public final class NrpcPacket extends AbstractIoPacket implements Externalizable, Message<NrpcPacket>, Schema<NrpcPacket>
{

    public static final byte START_BYTE = 0x3;
    public static final byte END_BYTE = 0x2;

    public static Schema<NrpcPacket> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static NrpcPacket getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final NrpcPacket DEFAULT_INSTANCE = new NrpcPacket();

    
    private Head head;
    private ByteString body;

    public NrpcPacket()
    {
        head = new Head();
    }

    // getters and setters

    // head

    public Head getHead()
    {
        return head;
    }

    public void setHead(Head head)
    {
        this.head = head;
    }

    // body

    public ByteString getBody()
    {
        return body;
    }

    public void setBody(ByteString body)
    {
        this.body = body;
    }

    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    public Schema<NrpcPacket> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public NrpcPacket newMessage()
    {
        return new NrpcPacket();
    }

    public Class<NrpcPacket> typeClass()
    {
        return NrpcPacket.class;
    }

    public String messageName()
    {
        return NrpcPacket.class.getSimpleName();
    }

    public String messageFullName()
    {
        return NrpcPacket.class.getName();
    }

    public boolean isInitialized(NrpcPacket message)
    {
        return true;
    }

    public void mergeFrom(Input input, NrpcPacket message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.head = input.mergeObject(message.head, Head.getSchema());
                    break;

                case 2:
                    message.body = input.readBytes();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, NrpcPacket message) throws IOException
    {
        if(message.head != null)
             output.writeObject(1, message.head, Head.getSchema(), false);


        if(message.body != null)
            output.writeBytes(2, message.body, false);
    }

    public String getFieldName(int number)
    {
        switch(number)
        {
            case 1: return "head";
            case 2: return "body";
            default: return null;
        }
    }

    public int getFieldNumber(String name)
    {
        final Integer number = __fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    private static final java.util.HashMap<String,Integer> __fieldMap = new java.util.HashMap<String,Integer>();
    static
    {
        __fieldMap.put("head", 1);
        __fieldMap.put("body", 2);
    }

    @Override
    public IoPacket newResponsePacket(IoPacket reqPacket, int ec, String message, Object body, Serializer serializer) throws Exception {
        NrpcPacket nrpcPacket = (NrpcPacket) reqPacket;
        byte[] bytes = U.EMPTY_BYTES;
        if (body != null && serializer != null) {
            bytes = serializer.serialize(body);
        }
        nrpcPacket.setErrCode(ec);
        nrpcPacket.setErrMsg(message);
        nrpcPacket.setBody(ByteString.copyFrom(bytes));
        return nrpcPacket;
    }

    @Override
    public Object getContent(Class clazz, Serializer serializer) throws Exception {
        Object request = serializer.deserialize(clazz,body.toByteArray());
        return request;
    }

    @Override
    public void setContent(Object content, Serializer serializer) throws Exception {
        this.content = content;
        this.body = ByteString.copyFrom(serializer.serialize(content));
    }


    @Override
    public Object getCmd() {
        return head.getServiceName();
    }

    @Override
    public void setCmd(Object cmd) {
        head.setServiceName((String)cmd);
    }

    @Override
    public Object getSubcmd() {
        return head.getServiceCmd();
    }

    @Override
    public void setSubcmd(Object subcmd) {
        head.setServiceCmd((String)subcmd);
    }

    @Override
    public Long getSeq() {
        return head.getSeq();
    }

    @Override
    public void setSeq(Long seq) {
        head.setSeq(seq);
    }
}