package com.graduate.common;

import com.graduate.bean.EmployeeBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-15  14:30
 * @Description：<描述>
 */
public class EmailRunnable implements Runnable {
    private EmployeeBean employeeBean;
    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;

    public EmailRunnable(EmployeeBean employeeBean,
                         JavaMailSender javaMailSender,
                         TemplateEngine templateEngine) {
        this.employeeBean = employeeBean;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }
    @Override
    public void run() {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(employeeBean.getEmail());
            helper.setFrom("w741069229@163.com");
            helper.setSubject("四川大学锦城学院：通知");
            Context ctx = new Context();
            ctx.setVariable("name", employeeBean.getName());
            ctx.setVariable("workID", employeeBean.getWorkID());
            ctx.setVariable("contractTerm", employeeBean.getContractTerm());
            ctx.setVariable("beginContract", employeeBean.getBeginContract());
            ctx.setVariable("endContract", employeeBean.getEndContract());
            ctx.setVariable("departmentName", employeeBean.getDepartmentName());
            ctx.setVariable("posName", employeeBean.getPosName());
            String mail = templateEngine.process("email.html", ctx);
            helper.setText(mail, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送失败");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
