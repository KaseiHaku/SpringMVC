package kasei.web.repository.dao;


import kasei.springcloud.webflux.repository.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends MongoRepository<Log, Long> {


}
