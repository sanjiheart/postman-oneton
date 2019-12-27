package tw.sanjiheart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import tw.sanjiheart.model.MailClientConf;
import tw.sanjiheart.service.MailService;
import tw.sanjiheart.service.ScheduleService;

@RestController
@RequestMapping("/mails")
public class MailController {

  @Autowired
  private ScheduleService scheduleService;

  @Autowired
  private MailService mailService;

  @GetMapping("/schedule")
  public ResponseEntity<Map<String, Boolean>> toggle(@RequestParam(defaultValue = "false") boolean enabled) {
    Map<String, Boolean> respBody = Maps.newConcurrentMap();
    if (enabled) {
      scheduleService.start();
      respBody.put("enabled", true);
      return new ResponseEntity<Map<String, Boolean>>(respBody, HttpStatus.OK);
    } else {
      scheduleService.stop();
      respBody.put("enabled", false);
      return new ResponseEntity<Map<String, Boolean>>(respBody, HttpStatus.OK);
    }
  }

  @PostMapping
  public ResponseEntity<MailClientConf> send() {
    return new ResponseEntity<MailClientConf>(mailService.send(), HttpStatus.CREATED);
  }

}
