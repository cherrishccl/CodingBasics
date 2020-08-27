package com.boot.basics.coding.spring.mail;

//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Date;

/**
 * @Author cherrishccl
 * @Date 2020/8/27 9:13
 * @Version 1.0
 * @Description
 */
@Slf4j
@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public MailVO sendMail(MailVO mailVO) {
        try {
            // 1.检测邮件
            checkMail(mailVO);
            // 2.发送邮件
            sendMimeMail(mailVO);
            return mailVO;
        } catch (Exception e) {
            log.error("发送邮件失败: {}", e);
            mailVO.setStatus("500");
            mailVO.setError(e.getMessage());
            return mailVO;
        }

    }
    private void sendMimeMail(MailVO mailVO) {
        try {
            // true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            // 邮件发信人
            messageHelper.setFrom(mailVO.getFrom());
            // 邮件收信人
            messageHelper.setTo(mailVO.getTo().split(","));
            // 邮件主题
            messageHelper.setSubject(mailVO.getSubject());
            // 邮件内容
            messageHelper.setText(mailVO.getText());
            // 抄送
            if (!StringUtils.isEmpty(mailVO.getCc())) {
                messageHelper.setCc(mailVO.getCc().split(","));
            }
            // 密送
            if (!StringUtils.isEmpty(mailVO.getBcc())) {
                messageHelper.setCc(mailVO.getBcc().split(","));
            }
            // 添加邮件附件
            if (null != mailVO.getAttachments()) {
                /*for (MultipartFile multipartFile : mailVO.getAttachments()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }*/
                for (File file : mailVO.getAttachments()) {
                    messageHelper.addAttachment(file.getName(), file);
                }
            }
            // 发送时间
            if (StringUtils.isEmpty(mailVO.getSentDate())) {
                mailVO.setSentDate(new Date());
                messageHelper.setSentDate(mailVO.getSentDate());
            }
            // 正式发送邮件
            javaMailSender.send(messageHelper.getMimeMessage());
            mailVO.setStatus("200");
            log.info("发送邮件成功：{}->{}", mailVO.getFrom(), mailVO.getTo());
        } catch (Exception e) {
            // 发送失败
            throw new RuntimeException(e);
        }
    }
    /**
     * 校验邮件
     * @param mailVO
     */
    private void checkMail(MailVO mailVO) {
        if (StringUtils.isEmpty(mailVO.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVO.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVO.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }
}
