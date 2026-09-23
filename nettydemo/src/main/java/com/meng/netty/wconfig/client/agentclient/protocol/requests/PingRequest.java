package com.meng.netty.wconfig.client.agentclient.protocol.requests;

import io.netty.buffer.ByteBuf;
import lombok.Builder;
import lombok.ToString;
import com.meng.netty.wconfig.client.enums.EnumClientMessageType;

import static com.meng.netty.wconfig.client.enums.EnumClientMessageType.PING_REQ;

@ToString
@Builder
public class PingRequest extends BaseRequest {

    @Override
    public int encode(ByteBuf out) {
        return 0;
    }

    @Override
    public EnumClientMessageType getType() {
        return PING_REQ;
    }
}
