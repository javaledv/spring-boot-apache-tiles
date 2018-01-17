package ml.ledv.spring.boot.apache.tiles.db.utils.impl;

import ml.ledv.spring.boot.apache.tiles.db.entity.BaseEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.PublisherEntity;
import ml.ledv.spring.boot.apache.tiles.db.utils.EntityCreator;
import org.springframework.stereotype.Service;

@Service("publisherCreator")
public class PublisherCreator implements EntityCreator {

    @Override
    public BaseEntity createEntity() {
        return new PublisherEntity();
    }
}
