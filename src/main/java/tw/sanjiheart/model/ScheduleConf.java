package tw.sanjiheart.model;

public class ScheduleConf {

  private String cron;

  public String getCron() {
    return cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }

  @Override
  public String toString() {
    return "ScheduleConf [cron=" + cron + "]";
  }

}
