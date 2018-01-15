package ml.ledv.spring.boot.apache.tiles.db.utils.impl;

import ml.ledv.spring.boot.apache.tiles.db.entity.BaseEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import ml.ledv.spring.boot.apache.tiles.db.utils.EntityCreator;
import org.springframework.stereotype.Service;

@Service("userCreator")
public class UserCreator implements EntityCreator {

    @Override
    public BaseEntity createEntity() {
        return new UserEntity();
    }
}
