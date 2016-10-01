package org.cardy.spring.dao;

import org.cardy.spring.model.Exec;
import java.util.List;

public interface ExecDAO {

    public void saveOrUpdate(Exec exec);

    public void delete(int execId);

    public Exec get(int execId);

    public List<Exec> list();
}
