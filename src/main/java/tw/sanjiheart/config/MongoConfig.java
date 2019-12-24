package tw.sanjiheart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Override
  public MongoClient mongoClient() {
    return MongoClients.create();
  }

  @Override
  protected String getDatabaseName() {
    return "postman-mad";
  }

  @Bean
  public MongoDbFactory mongoFactory() {
    return new SimpleMongoClientDbFactory(mongoClient(), getDatabaseName());
  }

  @Bean
  public MappingMongoConverter mappingMongoConverter() {
    MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoFactory()),
        new MongoMappingContext());
    converter.setMapKeyDotReplacement("_");
    converter.afterPropertiesSet();
    return converter;
  }

}
