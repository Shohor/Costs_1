package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.Cost;
import de.shokhor.costs.repository.CostRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
@Repository
public class JpaCostRepsitoryImpl implements CostRepository {
    public Cost save(Cost cost, int userId) {
        return null;
    }

    public boolean delete(int costId, int userId) {
        return false;
    }

    public Cost get(int costId, int userId) {
        return null;
    }

    public List<Cost> getAll(int userId) {
        return null;
    }

    public List<Cost> getAllByGroup(int userId, int groupId) {
        return null;
    }
}
