package tw.sanjiheart.service;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tw.sanjiheart.model.MailClientConf;
import tw.sanjiheart.model.MailServerConf;
import tw.sanjiheart.util.HttpException;

@Service
public class MailService {

  @Autowired
  private ConfService confService;

  private JavaMailSender javaMailSender() {
    MailServerConf serverConf = confService.loadMailServerConf();
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(serverConf.getHost());
    mailSender.setPort(serverConf.getPort());
    mailSender.setUsername(serverConf.getUsername());
    mailSender.setPassword(serverConf.getPassword());

    Properties props = mailSender.getJavaMailProperties();
    props.putAll(serverConf.getProperties());

    return mailSender;
  }

  public MailClientConf send() {
    try {
      MimeMessage message = javaMailSender().createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      MailClientConf clientConf = confService.loadMailClientConf();
      String[] toArr = new String[clientConf.getTo().size()];
      helper.setTo(clientConf.getTo().toArray(toArr));
      helper.setSubject(clientConf.getSubject());
      helper.setText(clientConf.getText());
      if (clientConf.isWithAttachments()) {
        clientConf.getAttachments().forEach(absPath -> {
          FileSystemResource file = new FileSystemResource(new File(absPath));
          try {
            helper.addAttachment(file.getFilename(), file);
          } catch (MessagingException e) {
            e.printStackTrace();
            throw new HttpException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
          }
        });
      }
      javaMailSender().send(message);
      return clientConf;
    } catch (MessagingException e) {
      e.printStackTrace();
      throw new HttpException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
    }
  }

}
