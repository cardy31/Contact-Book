package org.cardy.spring.dao;

import org.cardy.spring.model.Ref;

import java.util.List;

public interface RefDAO {

    public void saveOrUpdate(Ref ref);

    public void delete(int refId);

    public Ref get(int refId);

    public List<Ref> list();
}
