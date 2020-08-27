package com.boot.basics.coding.spring.mail;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.util.Date;

/**
 * @Author cherrishccl
 * @Date 2020/8/27 9:07
 * @Version 1.0
 * @Description
 */
@lombok.Data
public class MailVO {
    /** 邮件id */
    private String id;
    /** 邮件发送人 */
    private String from;
    /** 邮件接收人(多个邮箱则用逗号","隔开) */
    private String to;
    /** 邮件主题 */
    private String subject;
    /** 邮件内容 */
    private String text;
    /** 发送时间 */
    private Date sentDate;
    /** 抄送（多个邮箱则用逗号","隔开） */
    private String cc;
    /** 密送（多个邮箱则用逗号","隔开） */
    private String bcc;
    /** 状态 */
    private String status;
    /** 报错信息 */
    private String error;
    /** 邮件附件 */
    @JsonIgnore
    private File[] attachments;
}
