package org.cardy.spring.dao;

import org.cardy.spring.model.Account;

import java.util.List;

public interface AccountDAO {

    public void saveOrUpdate(Account account);

    public void delete(int accountId);

    public Account get(int accountId);

    public List<Account> list();

}
