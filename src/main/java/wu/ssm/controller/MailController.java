package wu.ssm.controller;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import wu.ssm.Utility;

    @Component
   public class MailController {
        private MailSender sender;
        private MailProperties mailProperties;

        public MailController(MailSender sender, MailProperties mailProperties) {
            this.sender = sender;
            this.mailProperties = mailProperties;
        }

        public void sendMail(String address, String title, String content) {
            Utility.log("发送邮件");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(mailProperties.getUsername());
            ;
            mailMessage.setSubject(title);
            mailMessage.setTo(address);
            mailMessage.setText(content);

            sender.send(mailMessage);
        }
    }

