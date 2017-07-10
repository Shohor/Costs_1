package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.Group;
import de.shokhor.costs.repository.GroupRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
@Repository
public class JpaGroupRepositoryImpl implements GroupRepository {
    public Group save(Group group) {
        return null;
    }

    public boolean delete(int groupId) {
        return false;
    }

    public Group get(int groupId) {
        return null;
    }

    public List<Group> getAll() {
        return null;
    }
}
