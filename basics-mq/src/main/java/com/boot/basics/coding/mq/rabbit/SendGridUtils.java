package com.boot.basics.coding.mq.rabbit;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2020/8/14 10:47
 * @Version 1.0
 * @Description 邮件发送工具
 */
public class SendGridUtils {
    private final String MAIL_REQUEST_ENDPOINT = "mail/send";
    private static final String FROM_ADDR = "123456@qq.com";
    private static final String MAIL_TYPE_TEXT = "text/plain";
    private static final String MAIL_TYPE_HTML = "text/html";
    private SendGrid sendGrid = null;
    public SendGridUtils(){
        sendGrid = new SendGrid("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");
    }

    /**
     * 发送带附件的HTML形式邮件
     *
     * @param toAddress 发送到的地址
     * @param subject   邮件的主题
     * @param content   邮件的内容参数,key为模板中标签的id值，value为该id标签中要填的值
     * @param filePath  邮件的html模板地址（该文件必须在resource目录下）
     * @param files     邮件的附件（可选，无附件也能发送成功）
     */
    public void sendHtmlMailWithAttachments(String toAddress, String subject, Map<String, String> content, String filePath, File... files) {
        String htmlStr = toHtmlStr(filePath, content);
        sendMailWithAttachments(FROM_ADDR, toAddress, subject, htmlStr, MAIL_TYPE_HTML, files);
    }

    /**
     * 发送带附件的普通邮件
     *
     * @param toAddress 发送到的地址
     * @param subject   邮件的主题
     * @param content   邮件的内容
     * @param files     邮件的附件（可选，无附件也能发送成功）
     */
    public void sendTextMailWithAttachments(String toAddress, String subject, String content, File... files) {
        sendMailWithAttachments(FROM_ADDR, toAddress, subject, content, MAIL_TYPE_TEXT, files);
    }


    /**
     * 发送带附件的邮件
     *
     * @param fromAddress 发送者的地址
     * @param toAddress   发送到的地址
     * @param subject     发送的主题
     * @param content     发送的内容
     * @param files       发送的附件，为数组形式
     */
    public void sendMailWithAttachments(String fromAddress, String toAddress, String subject, String content, String mailType, File... files) {
        try {
            //构建mail
            Mail mail = toMail(fromAddress, toAddress, subject, content, mailType);
            //构建attach并添加到mail中
            for (File file : files) {
                String fileName = file.getName();
                FileInputStream fileInputStream = new FileInputStream(file);
                Attachments attachments = new Attachments.Builder(fileName, fileInputStream).build();
                mail.addAttachments(attachments);
            }
            //发送mail
            sendMail(mail);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 构建mail
     *
     * @param from        发送人地址
     * @param to          发送到的地址
     * @param subject     邮件主题
     * @param content     邮件内容
     * @param messageType 邮件类型
     * @return 返回构建的邮件mail
     */
    private Mail toMail(String from, String to, String subject, String content, String messageType) {
        return new Mail(new Email(from), subject, new Email(to), new Content(messageType, content));
    }


    /**
     * 将map中的数据加入到模板中并返回模板的字符串形式
     *
     * @param filePath 模板所在的路径
     * @param content  要加入模板中的数据
     * @return 返回模板的字符串形式
     */
    public String toHtmlStr(String filePath, Map<String, String> content) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            Document htmlTemplate = new SAXReader().read(file);
            Element root = htmlTemplate.getRootElement();
            //为与数据中的key相同的id加入数据。
            Iterator<String> iterator = content.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                getNodes(root, "id", key).setText(content.get(key));
            }
            return htmlTemplate.asXML();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 方法描述：递归遍历子节点，根据属性名和属性值，找到对应属性名和属性值的那个子孙节点。
     *
     * @param node      要进行子节点遍历的节点
     * @param attrName  属性名
     * @param attrValue 属性值
     * @return 返回对应的节点或null
     */
    public Element getNodes(Element node, String attrName, String attrValue) {

        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
            final String name = attr.getName();// 属性名称
            final String value = attr.getValue();// 属性的值
//            System.out.println("属性名称：" + name + "---->属性值：" + value);
            if (attrName.equals(name) && attrValue.equals(value)) {
                return node;
            }
        }
        // 递归遍历当前节点所有的子节点
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (Element e : listElement) {// 遍历所有一级子节点
            Element temp = getNodes(e, attrName, attrValue);
            // 递归
            if (temp != null) {
                return temp;
            }
        }
        return null;
    }

    public void sendMail(String to, String subject, String content) throws IOException {
        sendMail(/*fromAddr*/FROM_ADDR, to, subject, content);
    }

    public void sendMail(String from, String to, String subject, String content) throws IOException {
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint(MAIL_REQUEST_ENDPOINT);
        Mail mail = toMail(from, to, subject, content, MAIL_TYPE_TEXT);
//        request.setBody("{\"personalizations\":[{\"to\":[{\"email\":\"" + to
//                + "\"}],\"subject\":\"" + subject + "\"}],\"from\":{\"email\":\"" + from + "\"}," +
//                "\"content\":[{\"type\":\"text/plain\",\"value\": \"" + content + "\"}]}");
        request.setBody(mail.build());
        Response response = sendGrid.api(request);
        System.out.println(response.getStatusCode() + "");
        System.out.println(response.getBody());
        System.out.println(response.getHeaders() + "");
    }


    public void sendHtmlMail(String toAddr, String subject, String context) throws IOException {
        sendHtmlMail(/*fromAddr*/FROM_ADDR, toAddr, subject, context);
    }

    public void sendHtmlMail(String fromAddr, String toAddr, String subject, String context) throws IOException {
//        Email from = new Email(fromAddr);
//        Email  to = new Email(toAddr);
//        Content content = new Content(MAIL_TYPE_HTML,context);
//        Mail mail = new Mail(from, subject, to, content);
//        sendMail(mail);

        sendMail(fromAddr, toAddr, subject, context, MAIL_TYPE_HTML);
    }


    public void sendMail(MailVO vo) throws IOException {
        Email from = new Email(vo.getFromAddress());
        from.setName(vo.getFromUser());

        Email to = new Email(vo.getToAddress());
        to.setName(vo.getToUser());
        Content content = new Content(vo.getType(), vo.getContent());
        Mail mail = new Mail(from, vo.getSubject(), to, content);

        sendMail(mail);
        System.out.println("邮件已经发送到："+ vo.getToAddress());
    }

    public void sendMail(String fromAddr, String toAddr, String subject, String context, String type) throws IOException {
        Email from = new Email(fromAddr);
        Email to = new Email(toAddr);
        Content content = new Content(type, context);
        Mail mail = new Mail(from, subject, to, content);
//        mail.personalization.get(0).addSubstitution("-name-", "Example User");
//        mail.personalization.get(0).addSubstitution("-city-", "Denver");
//        mail.setTemplateId("13b8f94f-bcae-4ec6-b752-70d6cb59f932");
        sendMail(mail);
    }

    private void sendMail(Mail mail) throws IOException {
//        SendGrid sendGrid = new SendGrid(/*sendGridApiKey*/uresParam.getSendGridApiKey());
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint(MAIL_REQUEST_ENDPOINT);
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println("" + response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders().toString());
        } catch (IOException ex) {
            throw ex;
        }
    }

    public static class MailVO {
        private String fromUser;
        private String fromAddress;
        private String toAddress;
        private String toUser;
        private String subject;
        private String content;
        private String type;

        public MailVO() {}

        public MailVO(String fromUser, String fromAddress, String toUser, String toAddress, String subject, String content, String type) {
            this.fromUser = fromUser;
            this.fromAddress = fromAddress;
            this.toAddress = toAddress;
            this.toUser = toUser;
            this.subject = subject;
            this.content = content;
            this.type = type;
        }

        public String getFromUser() {
            return fromUser;
        }

        public void setFromUser(String fromUser) {
            this.fromUser = fromUser;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getToUser() {
            return toUser;
        }

        public void setToUser(String toUser) {
            this.toUser = toUser;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
