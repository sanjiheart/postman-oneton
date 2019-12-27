package tw.sanjiheart.service;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  @Autowired
  private TaskScheduler taskScheduler;

  private ScheduledFuture<?> scheduledFuture;

  @Autowired
  private ConfService confService;

  @Autowired
  private MailService mailService;

  @Bean
  private TaskScheduler threadPoolTaskScheduler() {
    return new ThreadPoolTaskScheduler();
  }

  public ScheduledFuture<?> start() {
    stop();
    scheduledFuture = taskScheduler.schedule(new Runnable() {
      @Override
      public void run() {
        mailService.send();
      }
    }, new Trigger() {
      @Override
      public Date nextExecutionTime(TriggerContext triggerContext) {
        CronTrigger cronTrigger = new CronTrigger(confService.loadSchedule().getCron());
        return cronTrigger.nextExecutionTime(triggerContext);
      }
    });
    return scheduledFuture;
  }

  public boolean stop() {
    if (Objects.nonNull(scheduledFuture)) {
      return scheduledFuture.cancel(true);
    }
    return false;
  }

}
