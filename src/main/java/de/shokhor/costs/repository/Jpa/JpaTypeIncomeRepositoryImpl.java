package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.model.Income.TypeIncome;
import de.shokhor.costs.model.User.User;
import de.shokhor.costs.repository.TypeIncomeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaTypeIncomeRepositoryImpl implements TypeIncomeRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public TypeIncome save(TypeIncome typeIncome, int userId) {

        if (typeIncome.isNew())
        {
            em.persist(typeIncome);
            return typeIncome;
        }
        else
        {
            em.merge(typeIncome);
            return typeIncome;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(TypeIncome.DELETE)
                .setParameter("id", id)
                .setParameter("userId",userId)
                .executeUpdate()!=0;
    }

    @Override
    public TypeIncome get(int id, int userId) {
        return em.createNamedQuery(TypeIncome.GET, TypeIncome.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public List<TypeIncome> getAll(int userId) {
        return em.createNamedQuery(TypeIncome.GET_ALL, TypeIncome.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
