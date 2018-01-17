package ml.ledv.spring.boot.apache.tiles.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ml.ledv.spring.boot.apache.tiles.db.repository.jpa")
@EnableMongoRepositories(basePackages = "ml.ledv.spring.boot.apache.tiles.db.repository.mongo")
public class RepositoryConfig {
}
