package tw.sanjiheart.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class ScheduleService {

  @Autowired
  private MailService mailService;

  @Scheduled(cron = "0 0/15 * * * *")
  public void send() throws MessagingException {
    mailService.send();
  }

}
