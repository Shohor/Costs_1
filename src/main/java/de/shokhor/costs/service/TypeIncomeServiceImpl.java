package de.shokhor.costs.service;

import de.shokhor.costs.model.Income.TypeIncome;
import de.shokhor.costs.repository.TypeIncomeRepository;
import de.shokhor.costs.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class TypeIncomeServiceImpl implements TypeIncomeService {

    @Autowired
    private TypeIncomeRepository repository;

    @Override
    public TypeIncome save(TypeIncome typeIncome, int userId) {
        Assert.notNull(typeIncome, "TypeIncome must be not null");
        return ExceptionUtil.checkNotFoundWithId(repository.save(typeIncome, userId), typeIncome.getId());
    }

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId),id);

    }

    @Override
    public TypeIncome get(int id, int userId) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, userId),id);
    }

    @Override
    public List<TypeIncome> getAll(int userId) {
        return repository.getAll(userId);
    }
}
