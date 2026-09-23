package com.meng.netty.wconfig.client.agentclient.protocol.responses;

import io.netty.buffer.ByteBuf;
import lombok.Builder;
import lombok.ToString;
import com.meng.netty.wconfig.client.enums.EnumClientMessageType;

@Builder
@ToString
public class PingResponse extends BaseResponse {

    @Override
    public BaseResponse decode(ByteBuf in) throws Exception {
        return this;
    }

    @Override
    public EnumClientMessageType getType() {
        return EnumClientMessageType.PING_RESP;
    }
}
