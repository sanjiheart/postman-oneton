package tw.sanjiheart.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tw.sanjiheart.model.Conf;

public interface ConfRepo extends MongoRepository<Conf, String> {

}
