package tw.sanjiheart.model;

import java.util.Map;

import com.google.common.collect.Maps;

public class MailServerConf {

  private String host;

  private int port;

  private String username;

  private String password;

  private Map<String, String> properties = Maps.newConcurrentMap();

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }

  @Override
  public String toString() {
    return "MailServerConf [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password
        + ", properties=" + properties + "]";
  }

}
