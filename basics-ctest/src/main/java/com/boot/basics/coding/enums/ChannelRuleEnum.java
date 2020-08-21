package com.boot.basics.coding.enums;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 18:01
 * @Version 1.0
 * @Description
 */
public enum ChannelRuleEnum {
    TOUTITAO("toutiao", new ToutiaoChannelRule()),
    TENCENT("tencent", new TencentChannelRule()),
    ;
    private String name;
    private GeneralChannelRule channel;

    ChannelRuleEnum(String name, GeneralChannelRule channel) {
        this.name = name;
        this.channel = channel;
    }

    public static ChannelRuleEnum match(String name){
        for(ChannelRuleEnum ruleEnum : ChannelRuleEnum.values()){
            if(ruleEnum.name.equals(name)){
                return ruleEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public GeneralChannelRule getChannel() {
        return channel;
    }
}
