package tw.sanjiheart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.sanjiheart.model.MailClientConf;
import tw.sanjiheart.model.MailServerConf;
import tw.sanjiheart.service.ConfService;

@RestController
@RequestMapping("/confs")
public class ConfController {

  @Autowired
  private ConfService confService;

  @PutMapping("/mailServer")
  public ResponseEntity<MailServerConf> saveMailServer(@RequestBody MailServerConf mailServerConf) {
    return new ResponseEntity<MailServerConf>(confService.saveMailServerConf(mailServerConf), HttpStatus.OK);
  }

  @GetMapping("/mailServer")
  public ResponseEntity<MailServerConf> loadMailServer() {
    return new ResponseEntity<MailServerConf>(confService.loadMailServerConf(), HttpStatus.OK);
  }

  @PutMapping("/mailClient")
  public ResponseEntity<MailClientConf> saveMailClient(@RequestBody MailClientConf mailClientConf) {
    return new ResponseEntity<MailClientConf>(confService.saveMailClientConf(mailClientConf), HttpStatus.OK);
  }

  @GetMapping("/mailClient")
  public ResponseEntity<MailClientConf> loadMailClient() {
    return new ResponseEntity<MailClientConf>(confService.loadMailClientConf(), HttpStatus.OK);
  }

}
