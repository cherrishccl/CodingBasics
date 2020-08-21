package com.boot.basics.coding;

import com.boot.basics.coding.enums.ChannelRuleEnum;
import com.boot.basics.coding.enums.GeneralChannelRule;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 18:06
 * @Version 1.0
 * @Description
 */
public class EnumTest {
    public static void main(String[] args) {
        String channel = "toutiao";
        ChannelRuleEnum ruleEnum = ChannelRuleEnum.match(channel);
        GeneralChannelRule channelRule = ruleEnum.getChannel();
        channelRule.process();
    }
}
