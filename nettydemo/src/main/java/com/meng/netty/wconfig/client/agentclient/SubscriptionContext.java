package com.meng.netty.wconfig.client.agentclient;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import com.meng.netty.wconfig.client.WConfigCallback;
import com.meng.netty.wconfig.client.enums.EnumWConfigFileType;

import java.util.Set;

@ToString
@Data
@Builder
public class SubscriptionContext {
    private final Set<WConfigCallback> callBackSet;
    private final String localFile;
    private final EnumWConfigFileType fileType;
}
