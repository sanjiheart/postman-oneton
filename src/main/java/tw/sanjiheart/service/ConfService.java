package tw.sanjiheart.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.sanjiheart.model.Conf;
import tw.sanjiheart.model.ConfType;
import tw.sanjiheart.model.MailClientConf;
import tw.sanjiheart.model.MailServerConf;
import tw.sanjiheart.repo.ConfRepo;

@Service
public class ConfService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ConfRepo confRepo;

  @Autowired
  private ObjectMapper objMapper;

  private Map<String, Object> convertObjToConfigMap(Object obj) {
    return objMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
  }

  private Map<String, Object> getConfigMap(String id) {
    return confRepo.findById(id).get().getConfigMap();
  }

  public MailServerConf saveMailServerConf(MailServerConf mailServerConf) {
    Conf conf = new Conf();
    conf.setId(ConfType.MAIL_SERVER.name());
    conf.setConfigMap(convertObjToConfigMap(mailServerConf));
    logger.debug("conf: {}", conf);
    return objMapper.convertValue(confRepo.save(conf).getConfigMap(), MailServerConf.class);
  }

  public MailServerConf loadMailServerConf() {
    return objMapper.convertValue(getConfigMap(ConfType.MAIL_SERVER.name()), MailServerConf.class);
  }

  public MailClientConf saveMailClientConf(MailClientConf mailClientConf) {
    Conf conf = new Conf();
    conf.setId(ConfType.MAIL_CLIENT.name());
    conf.setConfigMap(convertObjToConfigMap(mailClientConf));
    logger.debug("conf: {}", conf);
    return objMapper.convertValue(confRepo.save(conf).getConfigMap(), MailClientConf.class);
  }

  public MailClientConf loadMailClientConf() {
    return objMapper.convertValue(getConfigMap(ConfType.MAIL_CLIENT.name()), MailClientConf.class);
  }

}
