package tw.sanjiheart.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Maps;

@Document
public class Conf {

  @Id
  private String id;

  private Map<String, Object> configMap = Maps.newConcurrentMap();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, Object> getConfigMap() {
    return configMap;
  }

  public void setConfigMap(Map<String, Object> configMap) {
    this.configMap = configMap;
  }

  @Override
  public String toString() {
    return "Conf [id=" + id + ", configMap=" + configMap + "]";
  }

}
