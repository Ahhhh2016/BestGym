package com.example.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import com.example.demo.domain.ActivityOrder;
/**
 * 消息接收者
 * *
 @author zhoubin
  * @since 1.0.0
 */
@Component
public class MailReceiver {

    private static final Logger logger =
            LoggerFactory.getLogger(MailReceiver.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;
    /**
     * 邮件发送
     */
    @RabbitListener(queues = "mail.welcome")
    public void handler(ActivityOrder order){

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
//发件人
            helper.setFrom(mailProperties.getUsername());
//收件人
            // helper.setTo(order.getUseremail());
            helper.setTo("1046629131@qq.com");
//主题
            helper.setSubject("Receipt");
//发送日期
            helper.setSentDate(new Date());
//邮件内容
            Context context= new Context();
            context.setVariable("name",order.getUsername());
            context.setVariable("date", order.getPaymentdate());
            context.setVariable("price",order.getPaymentmoney());
//            context.setVariable("departmentName",employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
//发送邮件
            javaMailSender.send(msg);
            System.out.println("mail send");
        } catch (MessagingException e) {
            logger.error("邮件发送失败=====>{}", e.getMessage());
        }
    }
}
