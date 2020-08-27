package com.boot.basics.coding.spring;

import com.boot.basics.coding.spring.mail.MailService;
import com.boot.basics.coding.spring.mail.MailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 10:35
 * @Version 1.0
 * @Description
 */
@SpringBootTest
public class SpringTest {
    @Autowired
    private MailService mailService;
    @Test
    void contextLoads() {
        MailVO mailVO = new MailVO();
        mailVO.setFrom("finance@yonghui.cn");
        mailVO.setTo("528050239@qq.com,1244537369@qq.com");
        mailVO.setSubject("test");
        mailVO.setText("test");
        mailVO.setAttachments(new File[]{new File("C:\\Users\\Administrator\\Downloads\\对账需求设计.docx")});
        mailService.sendMail(mailVO);
        System.out.println("00000000000000000000");
    }
}
