package tw.sanjiheart.model;

import java.util.List;

import com.google.common.collect.Lists;

public class MailClientConf {

  private List<String> to;

  private String subject;

  private String text;

  private boolean withAttachments;

  private List<String> attachments = Lists.newArrayList();

  public List<String> getTo() {
    return to;
  }

  public void setTo(List<String> to) {
    this.to = to;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isWithAttachments() {
    return withAttachments;
  }

  public void setWithAttachments(boolean withAttachments) {
    this.withAttachments = withAttachments;
  }

  public List<String> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<String> attachments) {
    this.attachments = attachments;
  }

  @Override
  public String toString() {
    return "MailClientConf [to=" + to + ", subject=" + subject + ", text=" + text + ", withAttachments="
        + withAttachments + ", attachments=" + attachments + "]";
  }

}
