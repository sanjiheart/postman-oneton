package tw.sanjiheart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.sanjiheart.model.MailClientConf;
import tw.sanjiheart.service.MailService;

@RestController
@RequestMapping("/mails")
public class MailController {

  @Autowired
  private MailService mailService;

  @PostMapping
  public ResponseEntity<MailClientConf> send() {
    return new ResponseEntity<MailClientConf>(mailService.send(), HttpStatus.CREATED);
  }

}
